package com.gogi.meatyou.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.OrderwithCouponDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ReckonDTO;
import com.gogi.meatyou.bean.SelectedProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.bean.UserPayDTO;
import com.gogi.meatyou.repository.MemberMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;
    
    @Autowired
	private JavaMailSenderImpl mailSender;
    
	private int authNumber; 
    
	@Autowired
	private HashMap memberMap;
    
	@Override
	public void userPaycomplete(ArrayList<MOrderDTO> list,int[] shop_num,int[]cp_num,String id) {
		memberMap.put("shop_num", shop_num);
		memberMap.put("cp_num", cp_num);
		memberMap.put("list", list);
		memberMap.put("id", id);
		
		mapper.userPay(list);
		
	}
	
	@Override
	public int ShoppingCartCNT(String shop_m_id) {
		return mapper.ShoppingCartCNT(shop_m_id);
	}

	@Override
	public int pick_P_CNT(String ppic_m_id) {
		return mapper.pick_P_CNT(ppic_m_id);
	}

	@Override
	public int pickCNT(String ppic_m_id) {
		return mapper.pickCNT(ppic_m_id);
	}

	//아이디 중복체크 mapper 접근
			@Override
			public int idCheck(String m_id) {
				int check = mapper.idCheck(m_id);
				System.out.println("check: " + check);
				return check;
			}	
			
			
			@Override
			public int eMailCheck(String email) {
				int check = mapper.eMailCheck(email);
				System.out.println("check: " + check);
				return check;
			}	
			@Override
			public int deleteCheck(String passwd) {
				int check = mapper.deleteCheck(passwd);
				System.out.println("check: " + check);
				return check;
			}	
			
    @Override
    public int insertMember(MemberDTO dto) {
        return mapper.insertMember(dto);
    }
    
    @Override
    public int  insertMember_Addr(String m_id) {
    	return mapper.insertMember_Addr(m_id);
    }
    
    @Override
    public int insertKaKao(MemberDTO dto) {
    	return mapper.insertKaKao(dto);
    }
  
    @Override
    public int ppickAndpickMeCount(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id, @Param("pm_num") int pm_num) {
        return mapper.ppickAndpickMeCount( pm_m_id, pm_c_id ,pm_num);
    }
    
    public void pick_me_delete(Model model,PickMeDTO pdto,ProductDTO ppdto,@Param("pm_m_id")String pm_m_id,
          @Param("pm_c_id") String pm_c_id
       ,@Param("pm_num") int pm_num ){
    }
 
    
    @Override
    public void pickMeInsert(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
        int pickCount = mapper.ppickAndpickMeCount(pm_m_id, pm_c_id, pm_num);

        if (pickCount > 0) {
            int deleteResult = mapper.deletePickMeByCId(pm_m_id, pm_c_id);
            if (deleteResult > 0) {
                model.addAttribute("message", " 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊而ㅵ뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빢�삕占쎌맶�뜝�럥�쑋�뜝�럩�쓥�뜝�럥�맶�뜝�럥�쑅占쎌젂筌믠뫖�맶�뜝�럥�쑅�뜏類㏃삕 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗�뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝�뜝�럩堉먪몭�뜉�삕占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽 占쎌녇占쎄틓占쎈뮛嶺뚯옓肉곤옙�굲�뜝�럡�렊 �뜝�럩�쐪占쎈쐻占쎈짗占쎌굲占쎈쨨�뜝�뜴�쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌띿뜴�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪占쎌쓦  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｋ뵃�삕亦낆꼪�쐻占쎈짗占쎌굲�뜝�럡肄э옙�쐻占쎈윪鈺곕‥�쐻占쎈윪占쎌읆占쎈쎗�뜝�띁伊덌옙維쀦뤃�먯삕占쎌맶�뜝�럥�쑅�뜝�럥�쑌 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗�뜝�럩�쟼�솻洹섎룱占쎈�뗰옙�쐻占쎈윞占쎈빟占쎈쐻占쎈윥獒뺤룊�삕占쎌맶�뜝�럥吏쀥뜝�럩援뀐옙�녇占쎈늅占쎄틟�뜝�럩援뀐옙�쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊�얘퍜�삕占쎄뎡�썒占쏙옙援욅몭�굩�삕占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃�먯삕占쎌맶�뜝�럥�쐾占쎄턀占쎄퍓�맶�뜝�럥�쑅�뜝�룞�삕占쎈쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞�뜝�럥�맶�뜝�럥�쑅占쎈쐻占쎈윞占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몥占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슣�돵�뜝�떥�궡留띰옙�쐻占쎈윥占쎌몗傭��끉猷딉옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 �뜝�럥�맶�뜝�럥�쑅勇싲즾維낉옙�굲�뜝�럩留뜹뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌띿뜴�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪占쎌쓦占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥裕싷옙�쐻占쎈윥占쎈쭓�뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뉐뜝�떥�궡留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁���삕占쎌맶�뜝�럥�쑅勇싲즾踰귨옙�맶�뜝�럥�쐾�뜝�럡�꼸�뜝�럥�맶�뜝�럥�쑋嶺뚮씭�뵛占쎌굲�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥�몭占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈뙑�ⓦ끉�굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵰占쎌몧�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁���삕占쎌맶�뜝�럥�쑋�뜝�럩�쓥占쎈쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊�얘퍜�삕占쎄뎡占쎈쐪筌먲옙占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥�뜝�룞�삕 占쎈쐻占쎈윥占쎄틦�뜝�뜦踰�占쎄콬�뜝�럡�렊 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맔占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵰占쎈폖�뜝�럥�뜲占쎈쐻占쎈윥占쎈쭍占쎈쐻占쎈윥筌뚮뜉�삕占쎌맶�뜝�럥�쐾�뜝�럥堉� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윞�굜�쉩�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빢�삕占쎌맶�뜝�럥�쑋嶺뚮쵎媛숋옙鍮앮쾬�꼻�삕�뜝�럩援뀐옙�쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빆�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윪獄�占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵯占쎈け�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫 .");
            }
        } else {
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", " 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞�뜝�럥�맶�뜝�럥�쑅占쎈쐻占쎈윞占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몥占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슣�돵�뜝�떥�궡留띰옙�쐻占쎈윥占쎌몗傭��끉猷딉옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 �뜝�럥�맶�뜝�럥�쑅勇싲즾維낉옙�굲�뜝�럩留뜹뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌띿뜴�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪占쎌쓦占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥裕싷옙�쐻占쎈윥占쎈쭓�뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕   占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｋ뵃�삕亦낆꼪�쐻占쎈짗占쎌굲�뜝�럡肄э옙�쐻占쎈윪鈺곕‥�쐻占쎈윪占쎌읆占쎈쎗�뜝�띁伊덌옙維쀦뤃�먯삕占쎌맶�뜝�럥�쑅�뜝�럥�쑌 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗�뜝�럩�쟼�솻洹섎룱占쎈�뗰옙�쐻占쎈윞占쎈빟占쎈쐻占쎈윥獒뺤룊�삕占쎌맶�뜝�럥吏쀥뜝�럩援뀐옙�녇占쎈늅占쎄틟�뜝�럩援뀐옙�쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊�얘퍜�삕占쎄뎡占쎈쐪筌먲옙占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥�뜝�룞�삕 占쎈쐻占쎈윥占쎄틦�뜝�뜦踰�占쎄콬�뜝�럡�렊 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맔占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵰占쎈폖�뜝�럥�뜲占쎈쐻占쎈윥占쎈쭍占쎈쐻占쎈윥筌뚮뜉�삕占쎌맶�뜝�럥�쐾�뜝�럥堉� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윞�굜�쉩�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빢�삕占쎌맶�뜝�럥�쑋嶺뚮쵎媛숋옙鍮앮쾬�꼻�삕�뜝�럩援뀐옙�쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빆�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윪獄�占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵯占쎈け�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫 .");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
       int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
       
       if (pickCount > 0) {
          int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
          if (deleteResult > 0) {
             model.addAttribute("message", " 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊而ㅵ뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빢�삕占쎌맶�뜝�럥�쑋�뜝�럩�쓥�뜝�럥�맶�뜝�럥�쑅占쎌젂筌믠뫖�맶�뜝�럥�쑅�뜏類㏃삕 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗�뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝�뜝�럩堉먪몭�뜉�삕占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽 占쎌녇占쎄틓占쎈뮛嶺뚯옓肉곤옙�굲�뜝�럡�렊 �뜝�럩�쐪占쎈쐻占쎈짗占쎌굲占쎈쨨�뜝�뜴�쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌띿뜴�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪占쎌쓦  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｋ뵃�삕亦낆꼪�쐻占쎈짗占쎌굲�뜝�럡肄э옙�쐻占쎈윪鈺곕‥�쐻占쎈윪占쎌읆占쎈쎗�뜝�띁伊덌옙維쀦뤃�먯삕占쎌맶�뜝�럥�쑅�뜝�럥�쑌 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗�뜝�럩�쟼�솻洹섎룱占쎈�뗰옙�쐻占쎈윞占쎈빟占쎈쐻占쎈윥獒뺤룊�삕占쎌맶�뜝�럥吏쀥뜝�럩援뀐옙�녇占쎈늅占쎄틟�뜝�럩援뀐옙�쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊�얘퍜�삕占쎄뎡�썒占쏙옙援욅몭�굩�삕占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃�먯삕占쎌맶�뜝�럥�쐾占쎄턀占쎄퍓�맶�뜝�럥�쑅�뜝�룞�삕占쎈쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞�뜝�럥�맶�뜝�럥�쑅占쎈쐻占쎈윞占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몥占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슣�돵�뜝�떥�궡留띰옙�쐻占쎈윥占쎌몗傭��끉猷딉옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 �뜝�럥�맶�뜝�럥�쑅勇싲즾維낉옙�굲�뜝�럩留뜹뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌띿뜴�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪占쎌쓦占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥裕싷옙�쐻占쎈윥占쎈쭓�뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뉐뜝�떥�궡留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁���삕占쎌맶�뜝�럥�쑅勇싲즾踰귨옙�맶�뜝�럥�쐾�뜝�럡�꼸�뜝�럥�맶�뜝�럥�쑋嶺뚮씭�뵛占쎌굲�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥�몭占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈뙑�ⓦ끉�굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵰占쎌몧�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁���삕占쎌맶�뜝�럥�쑋�뜝�럩�쓥占쎈쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊�얘퍜�삕占쎄뎡占쎈쐪筌먲옙占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥�뜝�룞�삕 占쎈쐻占쎈윥占쎄틦�뜝�뜦踰�占쎄콬�뜝�럡�렊 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맔占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵰占쎈폖�뜝�럥�뜲占쎈쐻占쎈윥占쎈쭍占쎈쐻占쎈윥筌뚮뜉�삕占쎌맶�뜝�럥�쐾�뜝�럥堉� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윞�굜�쉩�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빢�삕占쎌맶�뜝�럥�쑋嶺뚮쵎媛숋옙鍮앮쾬�꼻�삕�뜝�럩援뀐옙�쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빆�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윪獄�占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵯占쎈け�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫 .");
          }
       } else {
          int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
          if (insertResult > 0) {
             model.addAttribute("message", " 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞�뜝�럥�맶�뜝�럥�쑅占쎈쐻占쎈윞占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몥占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슣�돵�뜝�떥�궡留띰옙�쐻占쎈윥占쎌몗傭��끉猷딉옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 �뜝�럥�맶�뜝�럥�쑅勇싲즾維낉옙�굲�뜝�럩留뜹뜝�럥�맶占쎈쐻�뜝占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥占쎌쟽占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌띿뜴�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪占쎌쓦占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌몞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢履뗰옙�굲 占쎌녇占쎄틓占쎈뮛�뜝�럥裕싷옙�쐻占쎈윥占쎈쭓�뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕   占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｋ뵃�삕亦낆꼪�쐻占쎈짗占쎌굲�뜝�럡肄э옙�쐻占쎈윪鈺곕‥�쐻占쎈윪占쎌읆占쎈쎗�뜝�띁伊덌옙維쀦뤃�먯삕占쎌맶�뜝�럥�쑅�뜝�럥�쑌 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗�뜝�럩�쟼�솻洹섎룱占쎈�뗰옙�쐻占쎈윞占쎈빟占쎈쐻占쎈윥獒뺤룊�삕占쎌맶�뜝�럥吏쀥뜝�럩援뀐옙�녇占쎈늅占쎄틟�뜝�럩援뀐옙�쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占� 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊�얘퍜�삕占쎄뎡占쎈쐪筌먲옙占쏙옙�뜝�럥占쏙옙�쐻占쎈윪�얘꼈�쐻占쎈윥筌Ξ듬쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥�뜝�룞�삕 占쎈쐻占쎈윥占쎄틦�뜝�뜦踰�占쎄콬�뜝�럡�렊 占쎌녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맔占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵰占쎈폖�뜝�럥�뜲占쎈쐻占쎈윥占쎈쭍占쎈쐻占쎈윥筌뚮뜉�삕占쎌맶�뜝�럥�쐾�뜝�럥堉� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윞�굜�쉩�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빢�삕占쎌맶�뜝�럥�쑋嶺뚮쵎媛숋옙鍮앮쾬�꼻�삕�뜝�럩援뀐옙�쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빉�녇占쎄틓占쎈뮛�뜝�럥�뿭占쎈쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뿪占쎌굲 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윥筌ｏ옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗癲ル슪�맊甕겸뫜�삕占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁빆�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝占쎈쐻占쎈윪獄�占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵯占쎈け�뜝�럩留띰옙�쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫 .");
          }
       }
    }
    
  @Override
  public int couponCount(@Param("cp_m_id") String cp_m_id) {
    	return mapper.couponCount(cp_m_id);
    }
    
    
    @Override
   public  List<CouponDTO>  howmuchCoupon(@Param("cp_m_id") String cp_m_id) {
    	return mapper.howmuchCoupon(cp_m_id);
    }
    
    
    
    
    @Override
    public void deletePickMeByCId(String pm_m_id, String pm_c_id) {
        mapper.deletePickMeByCId(pm_m_id, pm_c_id);
    }
    @Override
    public void deletePickMeByCId2(String pm_m_id, String pm_c_id) {
       mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
    }
    
       @Override
       public void userUpdate(MemberDTO dto) {
          mapper.memberUpdate(dto);
       }
        
        
        @Override
       public int userDelete(MemberDTO dto) {
          return mapper.statusChange(dto);
       }
        @Override
        public int sallerDelete(MemberDTO dto) {
           return mapper.statusChange2(dto);
        }

        public int cusDelete(CusDetailDTO cdto,MemberDTO dto,@Param("m_id") String m_id) {
           return mapper.cusDelete(cdto,dto,m_id);
        }
       @Override
       public int statusChange(MemberDTO dto) {
          return mapper.statusAdminChange(dto);      
       }
       
        
       @Override
       public MemberDTO getUser(String m_id) {
          return mapper.member(m_id);
       }
       
       @Override          
       public List<MemAddressDTO>addressCheck(String add_m_id,MemberDTO mdto,MemAddressDTO adto,int add_num) {
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("add_m_id", add_m_id);
            List<MemAddressDTO> result = mapper.addressCheck(parameters);
         return result;

       }
       
       @Override        
       public List<String> combined_address(String id)  {
         
                      return mapper.combined_address(id);
        
          
       }
       @Override
       public int deleteAddr(@Param("add_num") int add_num ,String add_m_id) {
       return mapper.deleteAddr(add_num,add_m_id);   
       }
     
       
       @Override
       public  int addressCount(@Param("add_m_id")String add_m_id,@Param("add_num")int add_num  ) {
          return mapper.addressCount(add_m_id, add_num  );           
       }
          
       @Override         
       public void updateAddr(MemAddressDTO adto,@Param("add_m_id") String add_m_id,List<MemAddressDTO> AddrList,@Param("add_num")int add_num,@Param("add_mem_address1") String add_mem_address1,@Param("add_mem_address2") String add_mem_address2) {
          mapper. updateAddr( adto,  add_m_id,add_mem_address1,add_mem_address2,add_num);
       }
       @Override
       public void  insertAddr(MemAddressDTO  adto) {
          
          mapper.insertAddr(adto);  
          
       }
      
        @Override
          public int updateMemberStatus(MemberDTO dto) {
          return  mapper.updateMemberStatus(dto);
          }

          @Override
          public int insertIntoCusDetail(CusDetailDTO cdto) {
             return   mapper.insertIntoCusDetail(cdto);
          }
      
      
          
          
          public void shoppingCart(String m_id) {
             mapper.shoppingCart(m_id);
          };
          public void shoppingCart_seq(String m_id) {
             mapper.shoppingCart_seq(m_id);
          };
          
          ;
          
          public void pick_me(String m_id) {
             mapper.pick_me(m_id);
          }
          public void pick_me_seq(String m_id){
             mapper.pick_me_seq(m_id);
          };
          public void p_pick(String m_id) {
             mapper.p_pick(m_id);
             
          };
          public void p_pick_seq(String m_id) {
             mapper.p_pick_seq(m_id);
          }
          public void prefer(String m_id) {
             mapper.prefer(m_id);
          }


         @Override
         public List<ShoppingCartDTO> shoppingCartCheck(String m_id) {
               return mapper.shoppingcartCheck(m_id);
         };
      
         
         

         
         @Override
         public List<OrderwithCouponDTO> ShoppingCartAndProduct(String id,int page,Model model) {
        	 memberMap.clear();
        	 List<OrderwithCouponDTO> list = Collections.EMPTY_LIST;
        	 int count = 0;
     		int pageSize = 10;
     		int startRow = (page - 1) * pageSize + 1;
     		int endRow = page * pageSize;
     		
     		count = mapper.ShoppingCartCount(id);
     		System.out.println("===check "+id);
 			System.out.println("===check "+startRow);
 			System.out.println("===check "+endRow);
     		if (count > 0) {
     			
     			memberMap.put("id", id);
     			memberMap.put("start", startRow);
     			memberMap.put("end", endRow);

     			list = mapper.ShoppingCartAndProduct(memberMap);
     		}

     		model.addAttribute("list", list);
     		model.addAttribute("count", count);
     		model.addAttribute("page", page);
     		model.addAttribute("pageSize", pageSize);

     		// page
     		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
     		int startPage = (int) (page / 10) * 10 + 1;
     		int pageBlock = 10;
     		int endPage = startPage + pageBlock - 1;
     		if (endPage > pageCount) {
     			endPage = pageCount;
     		}
     		model.addAttribute("pageCount", pageCount);
     		model.addAttribute("startPage", startPage);
     		model.addAttribute("pageBlock", pageBlock);
     		model.addAttribute("endPage", endPage);
        	 
             return mapper.ShoppingCartAndProduct(memberMap);
         }

         
         
   
      
         
         @Override
         public void updateQuantity(int  shop_p_num,int  shop_quantity, String shop_m_id) {
            mapper.updateQuantity(shop_p_num, shop_quantity ,shop_m_id);
          }
         
         
         
 
          
         public List<ShoppingCartDTO> getShoppingCartItemsPaged2(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto,PDetailDTO pddto,List<CouponDTO> cList,CouponDTO cdto ) {
        	 int startRow = (page - 1) * pageSize + 1;
        	 int endRow = startRow + pageSize - 1;
        	 
        	 Map<String, Object> parameters = new HashMap<>();
        	 parameters.put("shop_m_id", shop_m_id);
        	 parameters.put("startRow", startRow);
        	 parameters.put("endRow", endRow);
        	 parameters.put("cList", cList);
        	 parameters.put("cp_num", cdto.getCp_num());
        	 parameters.put("cp_price", cdto.getCp_price());
        	 parameters.put("cList", cList);
        	 
        	 //   return mapper.getShoppingCartItemsPaged(parameters);
        	 List<ShoppingCartDTO> result = mapper.getShoppingCartItemsPaged2(parameters);
        	 return result;
         }
         public int CouponForyou(String shop_m_id,CouponDTO cdto ,ShoppingCartDTO sdto) {
        	return mapper.CouponForyou(shop_m_id,cdto,sdto);
         }
         
         
         public List<ShoppingCartDTO> orderpage(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto) {
            int startRow = (page - 1) * pageSize + 1;
            int endRow = startRow + pageSize - 1;
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("shop_m_id", shop_m_id);
            parameters.put("startRow", startRow);
            parameters.put("endRow", endRow);
            
            //   return mapper.getShoppingCartItemsPaged(parameters);
            List<ShoppingCartDTO> result = mapper.orderpage(parameters);
            return result;
         }
         
         
         
             @Override
             public int getTotalShoppingCartItems(String shop_m_id) {
                 return mapper.getTotalShoppingCartItems(shop_m_id);
             }
             
             
             @Override
             public int orderpageCartItems(String shop_m_id) {
                return mapper.orderpageCartItems(shop_m_id);
             }
             
                          
             
             @Override
            public int deleteCart(int shop_p_num,@Param("shop_m_id")String shop_m_id) {

             return mapper.deleteCart(shop_p_num,shop_m_id);

            }
      
             @Transactional
             @Override
             public void deleteSelectedItems(List<Long> selectedShopNums, String shop_m_id) {
                 Map<String, Object> paramMap = new HashMap<>();
                 paramMap.put("selectedShopNums", selectedShopNums);
                 paramMap.put("shop_m_id", shop_m_id);

                 System.out.print("list =======================" + selectedShopNums);
                 System.out.print("shop_m_id =======================" + shop_m_id);
                 
                 mapper.deleteSelectedItems(paramMap);
             }
             
             
             
            @Override
            public   List<PickMeDTO> pickMeCountPage(String pm_m_id, int page, int pageSize, PickMeDTO pdto, CusDetailDTO cdto){
                int startRow = (page  - 1) * pageSize + 1;
                int endRow = startRow + pageSize - 1;

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("pm_m_id", pm_m_id);
                parameters.put("startRow", startRow);
                parameters.put("endRow", endRow);

             //   return mapper.getShoppingCartItemsPaged(parameters);
                List<PickMeDTO> result = mapper.pickMeCountPage(parameters);
                return result;
            }
       
         
             @Override
             public int pickMeCount( @Param("pm_m_id")String pm_m_id,@Param("p_m_id")String p_m_id   ) {
                 return mapper.pickMeCount(pm_m_id,p_m_id  );
             }             
             @Override
            public int deleteHim(@Param("pm_num")int pm_num,@Param("pm_m_id")String pm_m_id){
                return mapper.deleteHim(pm_num,pm_m_id);
               }
             
             
               
             
             @Override
             public   List<PickMeDTO> SallerpickMeCountPage(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id, int page, int pageSize, PickMeDTO pdto ) {
                int startRow = (page  - 1) * pageSize + 1;
                int endRow = startRow + pageSize - 1;
                
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("pm_m_id", pm_m_id);
                parameters.put("pm_c_id", pm_c_id);
                parameters.put("startRow", startRow);
                parameters.put("endRow", endRow);
                
                //   return mapper.getShoppingCartItemsPaged(parameters);
                List<PickMeDTO> result = mapper.SallerpickMeCountPage(parameters);
                return result;
             }
             
             
             @Override
             public int SallerpickMeCount( @Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id) {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("pm_m_id", pm_m_id);
                parameters.put("pm_c_id", pm_c_id);
                return mapper.SallerpickMeCount(parameters);
             }             
             @Override
             public int SallerdeleteHim(@Param("pm_num")int pm_num,@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id){
                return mapper.SallerdeleteHim(pm_num,pm_m_id,pm_c_id);
             }
             
             
             
            @Override
            public   List<PPicDTO> pPickCountPages(@Param("ppic_m_id")String ppic_m_id,Map<String, Object> params,int page, int pageSize, PPicDTO ppdto, ProductDTO pdto,MemberDTO mdto,@Param("ppic_num")int ppic_num,CusDetailDTO cdto ){
                int startRow = (page - 1) * pageSize + 1;
                    int endRow = startRow + pageSize - 1;
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ppic_m_id",ppic_m_id);
                    parameters.put("ppic_num",ppic_num);
                   parameters.put("startRow",startRow);
                   parameters.put("endRow", endRow);
                   parameters.put("page", page);
                   parameters.put("endRow", endRow);
                  // parameters.put("ppic_m_id",mdto.getM_id());
                   
                   
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); 
                   
                    
                   
                    return result;                              
            }
            
            
            
            
            
             
             @Override
             public int pPickCount( @Param("ppic_m_id")String ppic_m_id,@Param("ppic_num")int ppic_num ) {
                return mapper.pPickCount(ppic_m_id,ppic_num);
             }
             @Override
             public int deleteP_item(@Param("ppic_num")int ppic_num,@Param("ppic_m_id")String ppic_m_id){
                
                return mapper.deleteP_item(ppic_num,ppic_m_id);
                
             }
         @Override
         public MemberDTO member(String m_id) {
            // TODO Auto-generated method stub
            return null;
         }

    
    
           
			/*
			   @Override public ShoppingCartDTO getSelectedProducts(int
			   shop_num, @Param("add_m_id") String add_m_id ) { return
			   mapper.getSelectedProducts(shop_num, add_m_id ); }
			 */
         @Override
         public  ShoppingCartDTO getSelectedProducts2(int shop_p_num, @Param("add_m_id") String add_m_id ) {
        	 return mapper.getSelectedProducts2(shop_p_num, add_m_id );
         }

		@Override
		public CouponDTO findCouponToCpNum(int cp_num) {
			return mapper.findCouponToCpNum(cp_num);
		}

		@Override
		public List<UserPayDTO> findshop_p_num(HashMap hashmap) {
			// TODO Auto-generated method stub
			return mapper.findshop_p_num(hashmap);
		 
		}
		
		
		
		@Override			 
		public int twoNextPay(OrderwithCouponDTO mdto,int shop_num,int order_p_num,String order_memo
				,@Param("order_m_id") String order_m_id,int order_cp_num,int order_p_price,
				@Param("order_dere_pay") int order_dere_pay ,@Param("order_addr") String order_addr,@Param("order_discount") int order_discount,@Param("order_quantity") int order_quantity
	    		,@Param("order_totalprice") int order_totalprice ) {
		
			return mapper.twoNextPay( mdto, shop_num ,  order_p_num, order_memo, order_m_id, order_cp_num,  order_p_price  , order_dere_pay, order_addr, order_discount, order_quantity
		    		, order_totalprice)  ;
		}

		@Override
		public List<MOrderDTO> paypage(@Param("order_m_id") String order_m_id , int page, int pageSize ){
			  int startRow = (page  - 1) * pageSize + 1;
              int endRow = startRow + pageSize - 1;
              
              Map<String, Object> parameters = new HashMap<>();
              parameters.put("order_m_id", order_m_id);
              parameters.put("startRow", startRow);
              parameters.put("endRow", endRow);
              
              //   return mapper.getShoppingCartItemsPaged(parameters);
              List<MOrderDTO> result = mapper.paypage(parameters);
              return result;
		}

		 
		@Override
		public int PaymentCount(@Param("order_m_id") String order_m_id ) {
			 Map<String, Object> parameters = new HashMap<>();
             parameters.put("pm_m_id", order_m_id);
             return mapper.PaymentCount(parameters);
		}
		
		
		//占쎈연疫꿸퀣苑� �겫占쏙옙苑� 占쎌뵠筌롫뗄�뵬
		
		public void makeRandomNumber() {
			// 占쎄텆占쎈땾占쎌벥 甕곕뗄�맄 111111 ~ 999999 (6占쎌쁽�뵳占� 占쎄텆占쎈땾)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			System.out.println("占쎌뵥筌앹빖苡뀐옙�깈 : " + checkNum);
			authNumber = checkNum;
		}
		
		@Override
		public String joinEmail(String email,String name,String phone,String m_id) {
			makeRandomNumber();
			memberMap.put("name",name);
			memberMap.put("email",email);
			memberMap.put("phone",phone);
			memberMap.put("m_id", m_id);
			System.out.println("=====m_id"+m_id);
			int result =mapper.checkEmail(memberMap);
			if(result==1) {
			String setFrom = "jaus0708@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
			String toMail = email;
			String title = "아이디/비밀번호 찾기 메일입니다."; // 이메일 제목 
			String content = 
					"홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 ! 
		            "<br><br>" + 
		            "인증 번호는 " + authNumber + "입니다." + 
		            "<br>" + 
				    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
			mailSend(setFrom, toMail, title, content);
			result = authNumber;
			}
			else {
				result= 0;
			}
			return Integer.toString(result) ;
		}

		@Override
		public List<CouponDTO> getProductCoupon(HashMap hashmap) {
			return mapper.getProductCoupon(hashmap);
		}

		@Override
		public OrderwithCouponDTO getProductInfo(int p_num) {
			return mapper.getProductInfo(p_num);
		}

		@Override
		public OrderwithCouponDTO getCartbyNum(HashMap hashmap) {
			return mapper.getCartbyNum(hashmap);
		}

		@Override
		public OrderwithCouponDTO getCouponNum(int cp_num) {
			return mapper.getCouponNum(cp_num);
		}

		

		
		//占쎌뵠筌롫뗄�뵬 占쎌읈占쎈꽊 筌롫뗄�꺖占쎈굡
				public void mailSend(String setFrom, String toMail, String title, String content) { 
					MimeMessage message = mailSender.createMimeMessage();
					// true 筌띲끆而삣첎誘れ뱽 占쎌읈占쎈뼎占쎈릭筌롳옙 multipart 占쎌굨占쎈뻼占쎌벥 筌롫뗄苑�筌욑옙 占쎌읈占쎈뼎占쎌뵠 揶쏉옙占쎈뮟.�눧紐꾩쁽 占쎌뵥�굜遺얜뎃 占쎄퐬占쎌젟占쎈즲 揶쏉옙占쎈뮟占쎈릭占쎈뼄.
					try {
						MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
						helper.setFrom(setFrom);
						helper.setTo(toMail);
						helper.setSubject(title);
						// true 占쎌읈占쎈뼎 > html 占쎌굨占쎈뻼占쎌몵嚥∽옙 占쎌읈占쎈꽊 , 占쎌삂占쎄쉐占쎈릭筌욑옙 占쎈륫占쎌몵筌롳옙 占쎈뼊占쎈떄 占쎈�볩옙�뮞占쎈뱜嚥∽옙 占쎌읈占쎈뼎.
						helper.setText(content,true);
						mailSender.send(message);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

		@Override
		public int findId(MemberDTO memberdto) {
			int a= 0;
			String dbName = null;
			String dbPhone = null;
			dbName = mapper.dbName(memberdto); //占쎈탵�뜮袁⑸꺗 占쎌뵠�뵳占�
			dbPhone =mapper.dbPhone(memberdto);// 占쎈탵�뜮袁⑸꺗 占쎌읈占쎌넅甕곕뜇�깈			
					
			String SName = memberdto.getM_name(); //占쎌뿯占쎌젾占쎈립 占쎌뵠�뵳占�
	    	String Sphone = memberdto.getPhone();  //占쎌뿯占쎌젾占쎈립 占쎌읈占쎌넅甕곕뜇�깈
	    	
	    	if (dbName != null && dbPhone != null && dbName.equals(SName) && dbPhone.equals(Sphone)) {
	            a = 1;
	        }
			return a;
		}

		@Override
		public void getDbId(Model model, MemberDTO memberdto) {
			String m_id = mapper.getDbId(memberdto);//占쎈뼄占쎌젫 占쎈툡占쎌뵠占쎈탵揶쏉옙占쎌죬占쎌궎疫뀐옙			
			model.addAttribute("m_id", m_id);
		}

		@Override
		public int findPw(MemberDTO memberdto) {
			int a= 0;
			String dbName = null;
			String dbPhone = null;
			String dbId = null;
			
			dbName = mapper.dbName(memberdto); //占쎈탵�뜮袁⑸꺗 占쎌뵠�뵳占�
			dbPhone =mapper.dbPhone(memberdto);// 占쎈탵�뜮袁⑸꺗 占쎌읈占쎌넅甕곕뜇�깈
			dbId = mapper.dbId(memberdto); // 占쎈탵�뜮袁⑸꺗 占쎈툡占쎌뵠占쎈탵
					
			String SName = memberdto.getM_name(); //占쎌뿯占쎌젾占쎈립 占쎌뵠�뵳占�
	    	String Sphone = memberdto.getPhone();  //占쎌뿯占쎌젾占쎈립 占쎌읈占쎌넅甕곕뜇�깈
			String SId = memberdto.getM_id(); // 占쎌뿯占쎌젾占쎈립 占쎈툡占쎌뵠占쎈탵
			
			if(dbName != null && dbId != null && dbPhone != null && dbName.equals(SName) && dbPhone.equals(Sphone) && dbId.equals(SId) ) {
				a = 1;
			}
			return a;
		}

		@Override
		public void getDbPw(Model model, MemberDTO memberdto) {
			String passwd = mapper.getDbPw(memberdto);//占쎈뼄占쎌젫 �뜮袁⑥쓰揶쏉옙占쎌죬占쎌궎疫뀐옙
			
			model.addAttribute("passwd", passwd);			
			model.addAttribute("id", memberdto.getM_id());			
			model.addAttribute("phone", memberdto.getPhone());			
		}

		@Override
		public void changePw(MemberDTO memberdto) { //�뜮袁⑨옙甕곕뜇�깈癰귨옙野껓옙
			mapper.changePw(memberdto);
		}
           
         
		@Override
		public String getAccessToken(String authorize_code) throws Throwable {
			String access_Token = "";
			String refresh_Token = "";
			String reqURL = "https://kauth.kakao.com/oauth/token";

			try {
				URL url = new URL(reqURL);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// POST �슂泥��쓣 �쐞�빐 湲곕낯媛믪씠 false�씤 setDoOutput�쓣 true濡�

				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				conn.setDoOutput(true);
				// POST �슂泥��뿉 �븘�슂濡� �슂援ы븯�뒗 �뙆�씪誘명꽣 �뒪�듃由쇱쓣 �넻�빐 �쟾�넚

				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");
				
				sb.append("&client_id=fcaac1b29853acd91d3df7f95bfa316f"); // REST_API�궎 蹂몄씤�씠 諛쒓툒諛쏆� key �꽔�뼱二쇨린
				sb.append("&redirect_uri=http://localhost:8080/member/loginpro"); // REDIRECT_URI 蹂몄씤�씠 �꽕�젙�븳 二쇱냼 �꽔�뼱二쇨린

				sb.append("&code=" + authorize_code);
				bw.write(sb.toString());
				bw.flush();

				// 寃곌낵 肄붾뱶媛� 200�씠�씪硫� �꽦怨�
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);

				// �슂泥��쓣 �넻�빐 �뼸�� JSON���엯�쓽 Response 硫붿꽭吏� �씫�뼱�삤湲�
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("response body : " + result);

				// jackson objectmapper 媛앹껜 �깮�꽦
				ObjectMapper objectMapper = new ObjectMapper();
				// JSON String -> Map
				Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
				});

				access_Token = jsonMap.get("access_token").toString();
				refresh_Token = jsonMap.get("refresh_token").toString();

				System.out.println("access_token : " + access_Token);
				System.out.println("refresh_token : " + refresh_Token);

				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return access_Token;
		}
		
		@Override
		public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable {
			 HashMap<String,Object> resultMap =new HashMap<>();
	         String reqURL = "https://kapi.kakao.com/v2/user/me";
	          try {
	              URL url = new URL(reqURL);
	              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	              conn.setRequestMethod("GET");

	             //�슂泥��뿉 �븘�슂�븳 Header�뿉 �룷�븿�맆 �궡�슜
	              conn.setRequestProperty("Authorization", "Bearer " + access_Token);

	              int responseCode = conn.getResponseCode();
	              System.out.println("responseCode : " + responseCode);
	              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

	              String br_line = "";
	              String result = "";


	              while ((br_line = br.readLine()) != null) {
	                  result += new String(URLDecoder.decode(br_line, "UTF-8"));
	              }
	             System.out.println("response:" + result);


	              JsonParser parser = new JsonParser();
	              JsonElement element = parser.parse(result);
	              JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	              JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	              String  m_id = element.getAsJsonObject().get("id").getAsString();
	           //   String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	              String email = kakao_account.getAsJsonObject().get("email").getAsString();
	              String name = kakao_account.getAsJsonObject().get("name").getAsString();
	              String birthyear = kakao_account.getAsJsonObject().get("birthyear").getAsString();
	              String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
	              String phone = kakao_account.getAsJsonObject().get("phone_number").getAsString();
	              
	         //     resultMap.put("nickname", nickname);
	              resultMap.put("m_id",m_id);
	              resultMap.put("email", email);
	              resultMap.put("name", name);
	              resultMap.put("birthyear", birthyear);
	              resultMap.put("birthday", birthday);
	              resultMap.put("phone_number", phone);
	              

	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	          return resultMap;
	      }
		public void tokenLogout(String accessToken) {
		    try {
		        // 移댁뭅�삤 濡쒓렇�븘�썐�쓣 �쐞�븳 URL
		        String logoutURL = "https://kapi.kakao.com/v1/user/logout";

		        // URL 媛앹껜 �깮�꽦
		        URL url = new URL(logoutURL);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		        // �슂泥� 硫붿꽌�뱶 �꽕�젙
		        connection.setRequestMethod("POST");

		        // Authorization �뿤�뜑�뿉 �븸�꽭�뒪 �넗�겙 異붽�
		        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

		        // �쓳�떟 肄붾뱶 �솗�씤
		        int responseCode = connection.getResponseCode();
		        System.out.println("Logout response code: " + responseCode);

		        // 濡쒓렇�븘�썐 �꽦怨� �뿬遺� �솗�씤 諛� 異붽� 濡쒖쭅 泥섎━

		    } catch (IOException e) {
		        e.printStackTrace();
		        // �삁�쇅 泥섎━瑜� �썝�븯�뒗 ��濡� �닔�뻾
		    }
		}
		
		@Override
		  public boolean memberList( @Param("m_id") String m_id) {
			// TODO Auto-generated method stub
			return MemberMapper.memberList(m_id);
		}
		@Override
		public int checkHim(String pm_m_id) {
			// TODO Auto-generated method stub
			return 0;
		}
             
             
             
}