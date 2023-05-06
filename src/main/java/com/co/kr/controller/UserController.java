package com.co.kr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.co.kr.domain.CategoryDomain;
import com.co.kr.domain.LoginDomain;
import com.co.kr.domain.ProblemDomain;
import com.co.kr.domain.RecordDomain;
import com.co.kr.domain.SearchDomain;
import com.co.kr.domain.WorkbookDomain;
import com.co.kr.service.UserService;
import com.co.kr.service.WorkbookService;
import com.co.kr.util.CommonUtils;
import com.co.kr.vo.LoginVO;
import com.co.kr.vo.TestingVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequestMapping(value = "/")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	WorkbookService workbookService;
	
	
	@PostMapping("login")
	public ModelAndView login(LoginVO loginDTO, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//session 처리 
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		// 중복체크
		Map<String, String> map = new HashMap();
		map.put("mbId", loginDTO.getId());
		map.put("mbPw", loginDTO.getPw());
		
		// 중복체크
		int dupleCheck = userService.mbDuplicationCheck(map);
		LoginDomain loginDomain = userService.mbGetId(map);
		
		if(dupleCheck == 0) {  
			String alertText = "없는 아이디이거나 패스워드가 잘못되었습니다. 가입해주세요";
			String redirectPath = "/";
			CommonUtils.redirect(alertText, redirectPath, response);
			return mav;
		}


		//현재아이피 추출
		String IP = CommonUtils.getClientIP(request);
		
		//session 저장
		session.setAttribute("ip",IP);
		session.setAttribute("seq", loginDomain.getMbSeq());
		session.setAttribute("id", loginDomain.getMbId());
		session.setAttribute("mbLevel", loginDomain.getMbLevel());
		
		mav.setViewName("redirect:/home");
		return mav;
	}
	
	
	// 어드민의 멤버추가 & 회원가입
	@PostMapping("signin/upload")
	public ModelAndView create(LoginVO loginVO, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		
		//session 처리 
		HttpSession session = request.getSession();
		
		//페이지 초기화
		String page = (String) session.getAttribute("page");
		if(page == null)page = "1";
		
		// 중복체크
		Map<String, String> map = new HashMap();
		map.put("mbId", loginVO.getId());
		map.put("mbPw", loginVO.getPw());
		
		
		// 중복체크
		int dupleCheck = userService.mbDuplicationCheck(map);
		System.out.println(dupleCheck);

		if(dupleCheck > 0) { // 가입되있으면  
			String alertText = "중복이거나 유효하지 않은 접근입니다";
			String redirectPath = "/singin";
			System.out.println(loginVO.getAdmin());
			CommonUtils.redirect(alertText, redirectPath, response);
		}else {
			
			//현재아이피 추출
			String IP = CommonUtils.getClientIP(request);
			
			//전체 갯수
			int totalcount = userService.mbGetAll();
			
			//db insert 준비
			LoginDomain loginDomain = LoginDomain.builder()
					.mbId(loginVO.getId())
					.mbPw(loginVO.getPw())
					.mbLevel((totalcount == 0) ? "3" : "2") // 최초가입자를 level 3 admin 부여
					.mbIp(IP)
					.mbUse("Y")
					.build();
			
			// 저장
			userService.mbCreate(loginDomain);
			
			session.setAttribute("ip",IP);
			session.setAttribute("id", loginDomain.getMbId());
			session.setAttribute("mbLevel", "2");
			mav.setViewName("redirect:/home");
		}
		
		return mav;

	};
	
	// 회원가입 화면
	@GetMapping("signin")
    public ModelAndView signIn() throws IOException {
		ModelAndView mav = new ModelAndView();
        mav.setViewName("signin.html"); 
        return mav;
    }
	
	//로그아웃
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.invalidate(); // 전체삭제
		mav.setViewName("index.html");
		return mav;
	}
	
	
	@RequestMapping("/info")
	public ModelAndView info(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		map.put("mbSeq", map.get("owner"));
		LoginDomain loginDomain=userService.mbSelectList(map);
		mav.addObject("userInfo",loginDomain);
		mav.setViewName("info.html");
		
		return mav;
	}
	
	@RequestMapping("/info/delete/{id}")
	public String infoDelete(@PathVariable("id") String id, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		
		map.put("mbSeq", map.get("owner"));
		userService.mbRemove(map);
		
		return "redirect:/";
	}
	
	@RequestMapping("/info/update/{id}")
	public ModelAndView infoUpdate(@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		map.put("mbSeq", map.get("owner"));
		LoginDomain loginDomain=userService.mbSelectList(map);
		
		mav.addObject("userInfo",loginDomain);
		mav.setViewName("workbook/infoUpdate.html");
		
		return mav;
	}

	@PostMapping("/info/update/upload")
	public String infoUpdateUpload(LoginDomain loginDomain) {
		
		userService.mbUpdate(loginDomain);
		
		return "redirect:/info";
	}
	
	
	
	
	/*
	 * 
	 * home
	 * 
	 */
	
	
	
	// 진입점
	@GetMapping("home")
	public ModelAndView home(){
		ModelAndView mav = new ModelAndView();

		mav.setViewName("home.html"); 
		return mav;
	}
	
	
	
	
	
	
	/*
	 * 
	 * workbook
	 * 
	 */
	
	@GetMapping("workbook")
	public ModelAndView workbook(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		List<WorkbookDomain> items = workbookService.selectAllWorkbook(map);
		System.out.println("items ==> "+ items);
		
		mav.addObject("items", items);
		mav.setViewName("workbook.html"); 
		
		return mav;
	}
	
	@GetMapping("workbook/new")
	public ModelAndView wbCreate(){
		ModelAndView mav=new ModelAndView();
		
		List<CategoryDomain> categoryList = workbookService.selectAllCategory();
		mav.addObject("categoryList", categoryList);
		mav.setViewName("workBook/wbCreate.html"); 
		
		return mav;
	}

	@PostMapping("workbook/new/upload")
	public String wbCreateUpload(WorkbookDomain workbookDomain, HttpServletRequest request, HttpServletRequest httpReq){
		Map map=CommonUtils.getMember(request);
		
		System.out.println("insert items ==> "+ workbookDomain);
		
		workbookDomain.setOwner((Integer)map.get("owner"));
		workbookService.insertWorkbook(workbookDomain,request,httpReq);
		
		return "redirect:/workbook";
	}
	
	@GetMapping("workbook/{id}")
	public ModelAndView wbDetail(@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		map.put("id", id);
		
		WorkbookDomain item = workbookService.selectOneWorkbook(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		
		List<ProblemDomain> items = workbookService.selectByWorkbook(map);
		System.out.println("detail("+id+") problem ==> "+ items);
		mav.addObject("items", items);

		map.put("id", item.getCategory());
		CategoryDomain category = workbookService.selectOneCategory(map);
		mav.addObject("category", category);
		
		mav.setViewName("workBook/wbDetail.html"); 
		
		return mav;
	}
	
	@GetMapping("workbook/update/{id}")
	public ModelAndView wbUpdate(@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		map.put("id", id);
		
		WorkbookDomain item = workbookService.selectOneWorkbook(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		

		List<CategoryDomain> categoryList = workbookService.selectAllCategory();
		mav.addObject("categoryList", categoryList);
		
		mav.setViewName("workBook/wbUpdate.html"); 
		
		return mav;
	}
	@PostMapping("workbook/update/upload")
	public String wbUpdateUpload(WorkbookDomain workbookDomain, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		
		System.out.println("update ==> "+ workbookDomain);
		
		workbookDomain.setOwner((Integer)map.get("owner"));
		workbookService.updateWorkbook(workbookDomain);
		
		return "redirect:/workbook";
	}
	
	@GetMapping("workbook/delete/{id}")
	public String wbDelete(@PathVariable("id") String id, HttpServletRequest request) {
		Map map=CommonUtils.getMember(request);
		
		System.out.println("delete ==> "+ id);
		
		map.put("id", id);
		workbookService.deleteWorkbook(map);

		return "redirect:/workbook";
	}
	
	
	/*
	 *
	 * problem
	 * 
	 */

	
	@GetMapping("workbook/pnew")
	public ModelAndView wbProblemNew(@RequestParam("workbook") String workbook) {
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("workbook", workbook);
		mav.setViewName("workBook/pCreate.html"); 
		
		return mav;
	}
	

	@PostMapping("workbook/pnew/upload")
	public String wbProblemNewUpload(ProblemDomain problemDomain) {
		
		System.out.println("insert items ==> "+ problemDomain);
		workbookService.insertProblem(problemDomain);
		
		return "redirect:/workbook/"+problemDomain.getWorkbook();
	}
	
	@GetMapping("problem/{id}")
	public ModelAndView pDetail(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		ProblemDomain item = workbookService.selectById(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		
		mav.setViewName("workBook/pDetail.html"); 
		
		return mav;
	}
	
	@GetMapping("problem/update/{id}")
	public ModelAndView pUpdate(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		
		ProblemDomain item = workbookService.selectById(map);
		System.out.println("detail("+id+") ==> "+ item);
		mav.addObject("item", item);
		mav.setViewName("workBook/pUpdate.html"); 
		
		return mav;
	}

	@PostMapping("problem/update/upload")
	public String pUpdateUpload(ProblemDomain problemDomain) {
		System.out.println("update ==> "+ problemDomain);
		
		workbookService.updateProblem(problemDomain);
		
		return "redirect:/problem/"+problemDomain.getId();
	}
	@GetMapping("problem/delete/{id}")
	public String pDelete(@PathVariable("id") String id) {
		System.out.println("delete ==> "+ id);
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		ProblemDomain problemDomain=workbookService.selectById(map);
		workbookService.deleteProblem(map);

		return "redirect:/workbook/"+problemDomain.getWorkbook();
	}

	
	/*
	 * 
	 * record
	 * 
	 */
	
	
	@GetMapping("testing")
	public ModelAndView testing(@RequestParam("workbook") String workbook) {
		System.out.println("testing ==> "+ workbook);
		
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", workbook);
		
		List<ProblemDomain> items = workbookService.selectByWorkbook(map);
		
		mav.addObject("workbook", workbook);
		mav.addObject("total", items.size());
		mav.addObject("current", "0");
		mav.addObject("score", "0");
		mav.setViewName("testing.html"); 
		
		return mav;
	}
	
	@PostMapping("testing")
	public ModelAndView testingProgress(TestingVO testingVO, HttpServletRequest request) {
		int current=testingVO.getCurrent();
		int workbook=testingVO.getWorkbook();
		int score=testingVO.getScore();
		List<Integer> list=(List) testingVO.getList();
		
		System.out.println("testing problem ==> "+ current);
		
		ModelAndView mav=new ModelAndView();
		
		Map map = new HashMap<String, String>();
		map.put("id", workbook);
		
		List<ProblemDomain> items = workbookService.selectByWorkbook(map);
		ProblemDomain item=null;
		
		// 정답 검사 -> 문제 로드 -> 끝내기
		//문제가 처음인가?	
		if(current>0) {
			
			// 오답 처리
			
			if(list==null) list=new ArrayList() {};
			
			// 오답 시 처리
			if(testingVO.getIsRight()==0) {
				int problem=testingVO.getProblem();
				list.add(problem);
			}else {
				// 정답 시 처리
				score++;
			}
			
			// 오답 넣기
			mav.addObject("list", list);
		}
		
		//어떤 문제를 가져오는가?
		if(current<=items.size()) {
			item=items.get(current-1);
		}else if(score<=items.size()){
			map.put("id", list.get(0));
			list.remove(0);
			item=workbookService.selectById(map);
		}

		//문제가 끝인가?
		if (score>items.size()){
			mav.addObject("end",0);
			RecordDomain recordDomain=new RecordDomain();
			recordDomain.setWorkbook(testingVO.getWorkbook());
			recordDomain.setOwner((Integer)CommonUtils.getMember(request).get("owner"));
			workbookService.insertRecord(recordDomain);
		}

		// 문제 넣기
		mav.addObject("item", item);

		//점수 초기화
		mav.addObject("score",score);
		mav.addObject("workbook", workbook);
		mav.addObject("total", items.size());
		mav.addObject("current", current);
		mav.setViewName("testing.html"); 
		
		return mav;
	}
	@GetMapping("record")
	public ModelAndView record(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		List<RecordDomain> items = workbookService.selectRecord(map);
		mav.addObject("items",items);
		mav.setViewName("record.html"); 
		
		return mav;
	}
	@GetMapping("record/delete/{id}")
	public String rDelete(@PathVariable("id") String id) {
		System.out.println("delete record ==> "+ id);
		
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteRecord(map);

		return "redirect:/record";
	}

	
	
	/*
	 * 
	 * category
	 * 
	 */
	

	@GetMapping("category")
	public ModelAndView category() {
		ModelAndView mav=new ModelAndView();
		
		List<CategoryDomain> categoryList = workbookService.selectAllCategory();

		mav.addObject("categoryList",categoryList);
		mav.setViewName("category.html"); 
		return mav;
	}
	@GetMapping("category/new")
	public String categoryCreate() {
		return "workbook/cCreate.html";
	}
	@PostMapping("category/new/upload")
	public String categoryCreate(CategoryDomain categoryDomain) {
		workbookService.insertCategory(categoryDomain);
		return "redirect:/category";
	}
	@GetMapping("category/delete/{id}")
	public String categoryCreateUpload(@PathVariable("id") String id) {
		Map map = new HashMap<String, String>();
		map.put("id", id);
		workbookService.deleteCategory(map);
		return "redirect:/category";
	}
	@GetMapping("category/{id}")
	public ModelAndView categoryDetail(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		Map map = new HashMap<String, String>();
		map.put("id", id);
		CategoryDomain category = workbookService.selectOneCategory(map);
		mav.addObject("item",category);
		mav.setViewName("workbook/cDetail.html"); 
		return mav;
	}
	@GetMapping("category/update/{id}")
	public ModelAndView categoryUpdate(@PathVariable("id") String id) {
		ModelAndView mav=new ModelAndView();
		Map map = new HashMap<String, String>();
		map.put("id", id);
		CategoryDomain category = workbookService.selectOneCategory(map);
		mav.addObject("item",category);
		mav.setViewName("workbook/cUpdate.html"); 
		return mav;
	}
	@PostMapping("category/update/upload")
	public String categoryUpdateUpload(CategoryDomain categoryDomain) {
		workbookService.updateCategory(categoryDomain);
		return "redirect:/category";
	}

	
	
	
	@GetMapping("search")
	public String search() {
		return "search.html";
	}
	@GetMapping("search/result")
	public ModelAndView searchResult(@RequestParam("search_query") String search_query, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		Map map=CommonUtils.getMember(request);
		
		map.put("search_query", search_query);
		List<SearchDomain> resultList= workbookService.searchProblem(map);
		mav.addObject("resultList",resultList);
		mav.setViewName("searchResult.html"); 
		
		
		return mav;
	}
	
	
}