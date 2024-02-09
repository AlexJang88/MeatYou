package com.gogi.meatyou.service;

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
    public int insertMember(MemberDTO dto) {
        return mapper.insertMember(dto);
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
                model.addAttribute("message", " �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢커占쎌맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앾옙�쐻占쎈윪占쎌읆占쎈쐻占쎈윥�젆戮⑸쐻占쎈윥獒뺧옙 �뜝�럥�맶�뜝�럥�쑅占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋占쎌뼐瑗덌옙�맶�뜝�럥�쐾�뜝�럥�젃 �솾�꺂�뒧筌잙뿰�삕占쎄뎡 占쎌뜏�뜝�룞�삕�뤃占썲뜝�럩援� �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅�뜝�럩�읇  �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥딉옙沅섇뜝�룞�삕占쎄콬�뜝�럩議롥뜝�럩�쟼�뛾占썼쥈�뫗援뀐옙�쐻占쎈윥占쎈윫 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅占쎌젂癰귘돦�뀋�뜝�럡�땽�뜝�럥裕쏉옙�쐻占쎈짗占쎌굲�솾�봾�꺋占쎌굲�뜝�럥�맶占쎈쐻�뜝占� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢逾껓옙�굲雅��굞瑗わ옙�맶�뜝�럥吏쀥뜝�럩援뀐옙�쐻占쎈윞�굜�껊쐻占쎈윥占쏙옙�뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃  �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘占쎈쐻占쎈윥�뜝�럡��占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑓�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅嶺뚯쉸占싸살맶�뜝�럥�쑅鶯ㅼ룊�삕 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 占쎈쐻占쎈윥鸚룐뫅�삕占쎌맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅�뜝�럩�읇�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 �솾�꺂�뒧占쎈뮚�뜝�럥�맋占쎈쐻占쎈윪筌랃옙  �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪇占싸살맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀯옙�쐻占쎈윥鸚룐벂�쐻占쎈윞占쎄섐占쎈쐻占쎈윪筌띾씛�삕占쎌맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 �뜝�럥�맶�뜝�럥�쑅�뜝�럥瑗� �뜝�럥�맶�뜝�럥�쑋�댖怨ㅼ삕 �뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮씮�쑕占쎌맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀯옙�쐻占쎈윪占쎌읆�뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢逾껓옙�굲�뜏類���占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쐾�뜝�럥占쏙옙 �뜝�럥�꺐占썩벀�걠占쎄뎡 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐦�삕 �뜝�럥�맶�뜝�럥�쑋嶺뚮씮�뼠占쎈데�뜝�럥�맆�뜝�럥琉덌옙�쐻占쎈윞占쎈뼃 �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럡肄욃뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앾옙�쐻占쎈윪筌묐같�빝沃섓옙占쎌굲�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앭뜝�럥�맶�뜝�럥�쑋�뜝�럩諭� �뜝�럥�맶�뜝�럥�쑋嶺뚮씭�몱占쎌맶�뜝�럥�쑅�뜝�럥�럪 .");
            }
        } else {
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", " �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘占쎈쐻占쎈윥�뜝�럡��占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑓�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅嶺뚯쉸占싸살맶�뜝�럥�쑅鶯ㅼ룊�삕 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 占쎈쐻占쎈윥鸚룐뫅�삕占쎌맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅�뜝�럩�읇�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 �솾�꺂�뒧占쎈뮚�뜝�럥�맋占쎈쐻占쎈윪筌랃옙   �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥딉옙沅섇뜝�룞�삕占쎄콬�뜝�럩議롥뜝�럩�쟼�뛾占썼쥈�뫗援뀐옙�쐻占쎈윥占쎈윫 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅占쎌젂癰귘돦�뀋�뜝�럡�땽�뜝�럥裕쏉옙�쐻占쎈짗占쎌굲�솾�봾�꺋占쎌굲�뜝�럥�맶占쎈쐻�뜝占� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢逾껓옙�굲�뜏類���占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쐾�뜝�럥占쏙옙 �뜝�럥�꺐占썩벀�걠占쎄뎡 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐦�삕 �뜝�럥�맶�뜝�럥�쑋嶺뚮씮�뼠占쎈데�뜝�럥�맆�뜝�럥琉덌옙�쐻占쎈윞占쎈뼃 �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럡肄욃뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앾옙�쐻占쎈윪筌묐같�빝沃섓옙占쎌굲�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앭뜝�럥�맶�뜝�럥�쑋�뜝�럩諭� �뜝�럥�맶�뜝�럥�쑋嶺뚮씭�몱占쎌맶�뜝�럥�쑅�뜝�럥�럪 .");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
       int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
       
       if (pickCount > 0) {
          int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
          if (deleteResult > 0) {
             model.addAttribute("message", " �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢커占쎌맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앾옙�쐻占쎈윪占쎌읆占쎈쐻占쎈윥�젆戮⑸쐻占쎈윥獒뺧옙 �뜝�럥�맶�뜝�럥�쑅占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋占쎌뼐瑗덌옙�맶�뜝�럥�쐾�뜝�럥�젃 �솾�꺂�뒧筌잙뿰�삕占쎄뎡 占쎌뜏�뜝�룞�삕�뤃占썲뜝�럩援� �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅�뜝�럩�읇  �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥딉옙沅섇뜝�룞�삕占쎄콬�뜝�럩議롥뜝�럩�쟼�뛾占썼쥈�뫗援뀐옙�쐻占쎈윥占쎈윫 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅占쎌젂癰귘돦�뀋�뜝�럡�땽�뜝�럥裕쏉옙�쐻占쎈짗占쎌굲�솾�봾�꺋占쎌굲�뜝�럥�맶占쎈쐻�뜝占� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢逾껓옙�굲雅��굞瑗わ옙�맶�뜝�럥吏쀥뜝�럩援뀐옙�쐻占쎈윞�굜�껊쐻占쎈윥占쏙옙�뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃  �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘占쎈쐻占쎈윥�뜝�럡��占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑓�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅嶺뚯쉸占싸살맶�뜝�럥�쑅鶯ㅼ룊�삕 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 占쎈쐻占쎈윥鸚룐뫅�삕占쎌맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅�뜝�럩�읇�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 �솾�꺂�뒧占쎈뮚�뜝�럥�맋占쎈쐻占쎈윪筌랃옙  �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪇占싸살맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀯옙�쐻占쎈윥鸚룐벂�쐻占쎈윞占쎄섐占쎈쐻占쎈윪筌띾씛�삕占쎌맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 �뜝�럥�맶�뜝�럥�쑅�뜝�럥瑗� �뜝�럥�맶�뜝�럥�쑋�댖怨ㅼ삕 �뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮씮�쑕占쎌맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀯옙�쐻占쎈윪占쎌읆�뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢逾껓옙�굲�뜏類���占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쐾�뜝�럥占쏙옙 �뜝�럥�꺐占썩벀�걠占쎄뎡 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐦�삕 �뜝�럥�맶�뜝�럥�쑋嶺뚮씮�뼠占쎈데�뜝�럥�맆�뜝�럥琉덌옙�쐻占쎈윞占쎈뼃 �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럡肄욃뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앾옙�쐻占쎈윪筌묐같�빝沃섓옙占쎌굲�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앭뜝�럥�맶�뜝�럥�쑋�뜝�럩諭� �뜝�럥�맶�뜝�럥�쑋嶺뚮씭�몱占쎌맶�뜝�럥�쑅�뜝�럥�럪 .");
          }
       } else {
          int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
          if (insertResult > 0) {
             model.addAttribute("message", " �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘占쎈쐻占쎈윥�뜝�럡��占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑓�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅嶺뚯쉸占싸살맶�뜝�럥�쑅鶯ㅼ룊�삕 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 占쎈쐻占쎈윥鸚룐뫅�삕占쎌맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅�뜝�럩�읇�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럥�쑌�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑋嶺뚮쪋�삕 �솾�꺂�뒧占쎈뮚�뜝�럥�맋占쎈쐻占쎈윪筌랃옙   �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥딉옙沅섇뜝�룞�삕占쎄콬�뜝�럩議롥뜝�럩�쟼�뛾占썼쥈�뫗援뀐옙�쐻占쎈윥占쎈윫 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅�뜝�럥�젘�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅占쎌젂癰귘돦�뀋�뜝�럡�땽�뜝�럥裕쏉옙�쐻占쎈짗占쎌굲�솾�봾�꺋占쎌굲�뜝�럥�맶占쎈쐻�뜝占� �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢逾껓옙�굲�뜏類���占쎈��뜝�럩逾겼뜝�럥痢ε뜝�럥�맶�뜝�럥�쐾�뜝�럥占쏙옙 �뜝�럥�꺐占썩벀�걠占쎄뎡 �솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐦�삕 �뜝�럥�맶�뜝�럥�쑋嶺뚮씮�뼠占쎈데�뜝�럥�맆�뜝�럥琉덌옙�쐻占쎈윞占쎈뼃 �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럡肄욃뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앾옙�쐻占쎈윪筌묐같�빝沃섓옙占쎌굲�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앯솾�꺂�뒧占쎈역�뜝�럩援� �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럥�맶�뜝�럥�쑋�뜝�럥泥� �뜝�럥�맶�뜝�럥�쑅嶺뚳퐢踰⑼옙�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐앭뜝�럥�맶�뜝�럥�쑋�뜝�럩諭� �뜝�럥�맶�뜝�럥�쑋嶺뚮씭�몱占쎌맶�뜝�럥�쑅�뜝�럥�럪 .");
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
		
		
		//�뿬湲곗꽌 遺��꽣 �씠硫붿씪
		
		public void makeRandomNumber() {
			// �궃�닔�쓽 踰붿쐞 111111 ~ 999999 (6�옄由� �궃�닔)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			System.out.println("�씤利앸쾲�샇 : " + checkNum);
			authNumber = checkNum;
		}
		
		@Override
		public String joinEmail(String email) {
			makeRandomNumber();
			String setFrom = "jaus0708@gmail.com"; // email-config�뿉 �꽕�젙�븳 �옄�떊�쓽 �씠硫붿씪 二쇱냼瑜� �엯�젰 
			String toMail = email;
			String title = "�븘�씠�뵒/鍮꾨�踰덊샇 李얘린 硫붿씪�엯�땲�떎."; // �씠硫붿씪 �젣紐� 
			String content = 
					"�솃�럹�씠吏�瑜� 諛⑸Ц�빐二쇱뀛�꽌 媛먯궗�빀�땲�떎." + 	//html �삎�떇�쑝濡� �옉�꽦 ! 
		            "<br><br>" + 
		            "�씤利� 踰덊샇�뒗 " + authNumber + "�엯�땲�떎." + 
		            "<br>" + 
				    "�빐�떦 �씤利앸쾲�샇瑜� �씤利앸쾲�샇 �솗�씤���뿉 湲곗엯�븯�뿬 二쇱꽭�슂."; //�씠硫붿씪 �궡�슜 �궫�엯
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
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

		

		
		//�씠硫붿씪 �쟾�넚 硫붿냼�뱶
				public void mailSend(String setFrom, String toMail, String title, String content) { 
					MimeMessage message = mailSender.createMimeMessage();
					// true 留ㅺ컻媛믪쓣 �쟾�떖�븯硫� multipart �삎�떇�쓽 硫붿꽭吏� �쟾�떖�씠 媛��뒫.臾몄옄 �씤肄붾뵫 �꽕�젙�룄 媛��뒫�븯�떎.
					try {
						MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
						helper.setFrom(setFrom);
						helper.setTo(toMail);
						helper.setSubject(title);
						// true �쟾�떖 > html �삎�떇�쑝濡� �쟾�넚 , �옉�꽦�븯吏� �븡�쑝硫� �떒�닚 �뀓�뒪�듃濡� �쟾�떖.
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
			dbName = mapper.dbName(memberdto); //�뵒鍮꾩냽 �씠由�
			dbPhone =mapper.dbPhone(memberdto);// �뵒鍮꾩냽 �쟾�솕踰덊샇			
					
			String SName = memberdto.getM_name(); //�엯�젰�븳 �씠由�
	    	String Sphone = memberdto.getPhone();  //�엯�젰�븳 �쟾�솕踰덊샇
	    	
	    	if (dbName != null && dbPhone != null && dbName.equals(SName) && dbPhone.equals(Sphone)) {
	            a = 1;
	        }
			return a;
		}

		@Override
		public void getDbId(Model model, MemberDTO memberdto) {
			String m_id = mapper.getDbId(memberdto);//�떎�젣 �븘�씠�뵒媛��졇�삤湲�			
			model.addAttribute("m_id", m_id);
		}

		@Override
		public int findPw(MemberDTO memberdto) {
			int a= 0;
			String dbName = null;
			String dbPhone = null;
			String dbId = null;
			
			dbName = mapper.dbName(memberdto); //�뵒鍮꾩냽 �씠由�
			dbPhone =mapper.dbPhone(memberdto);// �뵒鍮꾩냽 �쟾�솕踰덊샇
			dbId = mapper.dbId(memberdto); // �뵒鍮꾩냽 �븘�씠�뵒
					
			String SName = memberdto.getM_name(); //�엯�젰�븳 �씠由�
	    	String Sphone = memberdto.getPhone();  //�엯�젰�븳 �쟾�솕踰덊샇
			String SId = memberdto.getM_id(); // �엯�젰�븳 �븘�씠�뵒
			
			if(dbName != null && dbId != null && dbPhone != null && dbName.equals(SName) && dbPhone.equals(Sphone) && dbId.equals(SId) ) {
				a = 1;
			}
			return a;
		}

		@Override
		public void getDbPw(Model model, MemberDTO memberdto) {
			String passwd = mapper.getDbPw(memberdto);//�떎�젣 鍮꾨쾲媛��졇�삤湲�
			
			model.addAttribute("passwd", passwd);			
			model.addAttribute("id", memberdto.getM_id());			
			model.addAttribute("phone", memberdto.getPhone());			
		}

		@Override
		public void changePw(MemberDTO memberdto) { //鍮꾨�踰덊샇蹂�寃�
			mapper.changePw(memberdto);
		}
           
         
            
             
             
             
}