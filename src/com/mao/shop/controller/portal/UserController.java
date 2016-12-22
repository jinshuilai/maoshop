package com.mao.shop.controller.portal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.CustShipaddr;
import com.mao.shop.po.Customer;
import com.mao.shop.po.OrderAddr;
import com.mao.shop.po.OrderItem;
import com.mao.shop.po.Orders;
import com.mao.shop.po.ProductFeedback;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.po.Region;
import com.mao.shop.service.FeedbackService;
import com.mao.shop.service.OrderService;
import com.mao.shop.service.ShipAddrService;
import com.mao.shop.service.UserService;
import com.mao.shop.utils.MD5;
import com.mao.shop.utils.MaoUtils;
import com.mao.shop.utils.PageBean;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShipAddrService shipAddrService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private FeedbackService feedbackService;
	
	//ע��ҳ��
	@RequestMapping("/toRegist.do")
	public String toRegist() {
		
		return "portal/user/regist";
	}
	
	//ע���û�
	@RequestMapping("/register.do")
	public String register(HttpSession session,Customer customer,String captcha,Model model) {
		String rightCap = (String) session.getAttribute("piccode");
		if(!StringUtils.equalsIgnoreCase(captcha, rightCap)){
			model.addAttribute("tip", "��֤�����");
			return "portal/user/regist";
		}
		if (!StringUtils.isNotBlank(customer.getUserName()) || !StringUtils.isNotBlank(customer.getPassword())) {
			return "portal/user/regist";
		}
		Boolean result = userService.selectByName(customer.getUserName());
		if (!result) {
			model.addAttribute("name", "�û����Ѵ���");
			return "portal/user/regist";
		}
		customer.setPassword(new MD5().GetMD5Code(customer.getPassword()));
		customer.setRegistryAt(new Date());
		userService.saveUser(customer);
		
		String info = MaoUtils.readProp("regist_success");
		model.addAttribute("message", info);
		return "portal/info";
	}
	
	/**
	 * ������֤��ͼƬ
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getImage.do")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 System.out.println("#######################�������ֺ���ĸ����֤��#######################");  
	        BufferedImage img = new BufferedImage(100, 40,BufferedImage.TYPE_INT_RGB);  
	        // �õ���ͼƬ�Ļ�ͼ����    
	        Graphics g = img.getGraphics();  
	        Random r = new Random();  
	        Color c = new Color(200, 150, 255);  
	        g.setColor(c);  
	        // �������ͼƬ����ɫ    
	        g.fillRect(0, 0, 100, 40);  
	        // ��ͼƬ��������ֺ���ĸ    
	        StringBuffer sb = new StringBuffer();  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	        int index, len = ch.length;  
	        for (int i = 0; i < 4; i++) {  
	            index = r.nextInt(len);  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));  
	            // �����  ����ʹ�С                      
	            g.drawString("" + ch[index], (i * 20) + 3, 35);  
	            //дʲô���֣���ͼƬ ��ʲôλ�û�    
	            sb.append(ch[index]);  
	        }  
	        //��ͼƬ�ϵ� ֵ�洢��session�У��Ա�����֤
	        request.getSession().setAttribute("piccode", sb.toString());  
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	//��¼ҳ��
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		
		return "portal/user/login";
	}
	
	//��¼
	@RequestMapping("/login.do")
	public String login(HttpSession session,String username,String password,String captcha,Model model) {
		String rightCap = (String) session.getAttribute("piccode");
		if(!StringUtils.equalsIgnoreCase(captcha, rightCap)){
			model.addAttribute("tip", "��֤�����");
			return "portal/user/login";
		}
		password = new MD5().GetMD5Code(password);
		Map<String, String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		Customer user = userService.selectUserByUserPass(map);
		if (user == null) {
			model.addAttribute("tip", "�û�������������!");
			return "portal/user/login";
		}
		session.setAttribute("user", user);
		return "redirect:home/index.do";
	}
	
	//ajax��¼
	@RequestMapping("/loginAjax.do")
	public void loginAjax(String username,String password,HttpSession session,PrintWriter out) {
		password = new MD5().GetMD5Code(password);
		Map<String, String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		Customer user = userService.selectUserByUserPass(map);
		if (user == null) {
			out.write("userpass_error");
		}else {
			session.setAttribute("user", user);
			out.write("userpass");
		}
	}
	
	@RequestMapping("/getUser.do")
	public void getUser(HttpSession session,HttpServletResponse response) {
		Customer customer = (Customer) session.getAttribute("user");
		JSONObject jo = new JSONObject();
		jo.accumulate("user", customer);
		String result = jo.toString();
		MaoUtils.printJSON(result, response);
	}
	
	@RequestMapping("/exit.do")
	public String exit(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/index";
	}
	
	//��ת��������
	@RequestMapping("/home/index.do")
	public String toPersonIndex() {
		
		return "portal/user/index";
	}
	
	//��ת���ջ���ַ
	@RequestMapping("/home/toShipAddr.do")
	public String toShipAddr(HttpSession session,Model model,Integer id) {
		List<Region> province = shipAddrService.selectProvince();
		model.addAttribute("rList", province);
		Customer customer = (Customer) session.getAttribute("user");
		List<CustShipaddr> saList = shipAddrService.selectByUserId(customer.getCid());
		model.addAttribute("saList", saList);
		
		if (id != null) {
			CustShipaddr ship = shipAddrService.selectByShipId(id);
			model.addAttribute("ship", ship);
			List<Region> cList = shipAddrService.selectRegionByParentId(ship.getProvinceId());
			model.addAttribute("cList", cList);
			List<Region> dList = shipAddrService.selectRegionByParentId(ship.getCityId());
			model.addAttribute("dList", dList);
		}
		return "portal/user/shipaddr";
	}
	
	//ajax���ص���
	@RequestMapping("/loadOption.do")
	public void loadOption(Short parentId,HttpServletResponse response) {
		List<Region> rList = shipAddrService.selectRegionByParentId(parentId);
		JSONObject jo = new JSONObject();
		jo.accumulate("rList", rList);
		String result = jo.toString();
		MaoUtils.printJSON(result, response);
	}
	
	//����ջ���ַ
	@RequestMapping("/home/addShipAddr.do")
	public String addShipAddr(CustShipaddr shipaddr,HttpSession session) {
		Customer customer = (Customer) session.getAttribute("user");
		shipaddr.setCustomerId(customer.getCid());
		shipAddrService.saveShipAddr(shipaddr);
		return "redirect:toShipAddr.do";
	}
	
	//�޸��ջ���ַ
	@RequestMapping("/home/modifyShipAddr.do")
	public String modifyShipAddr(CustShipaddr shipaddr,HttpSession session) {
		Customer customer = (Customer) session.getAttribute("user");
		shipaddr.setCustomerId(customer.getCid());
		shipAddrService.updateShipAddr(shipaddr);
		return "redirect:toShipAddr.do";
	}
	
	//�޸�Ĭ�ϵ�ַ
	@RequestMapping("/home/changeAddrDef.do")
	public String changeAddrDef(Integer id,HttpSession session){
		Customer customer = (Customer) session.getAttribute("user");
		shipAddrService.updateShipAddrDefault(id,customer.getCid());
		return "redirect:toShipAddr.do";
	}
	
	//ɾ���ջ���ַ
	@RequestMapping("/home/delShipAddr.do")
	public String delShipAddr(Integer id) {
		shipAddrService.deleteByShipid(id);
		return "redirect:toShipAddr.do";
	}
	
	//�鿴�ҵĶ���
	@RequestMapping("/home/toOrder.do")
	public String toOrder(Integer page,Integer s,HttpSession session,Model model) {
		if (page == null) {
			page = 1;
		}
		Customer customer = (Customer) session.getAttribute("user");
		PageBean pageBean = orderService.selectAllByUserId(customer.getCid(),page,s);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("stat", s);
		return "portal/user/orders";
	}
	
	//�鿴��Ʒ����
	@RequestMapping("/home/snapshot.do")
	public String snapshot(Integer id,Model model){
		ProductSnapshot snapshot = orderService.selectSnapshot(id);
		model.addAttribute("snapshot", snapshot);
		return "portal/product/snapshot";
	}
	
	//ȷ���ջ�
	@RequestMapping("/home/receipt.do")
	public String receipt(String sn,HttpSession session) {
		Customer customer = (Customer) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("status", Orders.RECEIPT_NODEVAL);
		map.put("userId", customer.getCid());
		map.put("sn", sn);
		map.put("dealtime", new Date());
		orderService.updateStatus(map);
		return "redirect:toOrder.do";
	}
	
	//ǰ������
	@RequestMapping("/home/toFeedback.do")
	public String toFeedback(Integer id,HttpSession session,Model model) {
		Customer customer = (Customer) session.getAttribute("user");
		Orders orders = orderService.selectDetailByOid(id);
		if (orders.getCustId().intValue() != customer.getCid().intValue()) {
			model.addAttribute("message", "��û��Ȩ�޽��иò���");
			return "portal/info";
		}
		model.addAttribute("order", orders);
		return "portal/user/feedback";
	}
	
	//������Ʒ
	@RequestMapping("/home/feedback.do")
	public String feedback(Integer oid,HttpServletRequest request,Model model) {
		Customer cust = (Customer) request.getSession().getAttribute("user");
		Orders orders = orderService.selectDetailByOid(oid);
		List<ProductFeedback> fbList = new ArrayList<ProductFeedback>();
		for (OrderItem item : orders.getItemList()) {
			Integer pid = item.getProductId();
			String lever = request.getParameter("fbLever"+pid);
			String content = request.getParameter("fbtext"+pid);
			String niming = request.getParameter("fbnm"+pid);
			if (niming == null) {
				niming = "0";
			}
			ProductFeedback pf = new ProductFeedback();
			pf.setItemId(item.getItemId());
			pf.setFbLevel(new Short(lever));
			pf.setCustId(cust.getCid());
			pf.setContent(content);
			pf.setAnonymous(new Byte(niming));
			pf.setCreatedAt(new Date());

			fbList.add(pf);
		}
		feedbackService.saveFeedBack(fbList);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("status", Orders.COMPLEPE);
		map.put("userId", cust.getCid());
		map.put("sn", orders.getOrderSn());
		orderService.updateStatus(map);
		model.addAttribute("message", "���۳ɹ���");
		return "portal/info";
	}
	
	//�鿴��������
	@RequestMapping("/home/orderDetail.do")
	public String orderDetail(Integer id,Model model) {
		Orders orders = orderService.selectDetailByOid(id);
		model.addAttribute("order", orders);
		OrderAddr addr = orderService.selectShipByOrderId(id);
		model.addAttribute("addr", addr);
		return "portal/user/orderdetail";
	}
	
	//�鿴����
	@RequestMapping("/home/seeFeedback.do")
	public String seeFeedback(Integer id,HttpSession session,Model model) {
		Customer customer = (Customer) session.getAttribute("user");
		ProductFeedback feedback = feedbackService.selectByItemId(id,customer.getCid());
		model.addAttribute("item", feedback);
		return "portal/user/seefeedback";
	}
}
