package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductMorderDTO;
import com.gogi.meatyou.bean.PurchaseMemberListDTO;


public interface CustomersMapper {

	public void productUp(ProductDTO productdto); //��ǰ���
	public int productCurrval();//��ϵ� ��ǰ��ȣ ��������
	public void P_DETAILUp(PDetailDTO pdetaildto);  // ��ǰ�󼼵��
	
	public int itemcount(String id); // ��ǰ�����������  �� ��� ����
	public int paycount(String id); // ǰ�� ��������� ����	
	public int itemcounting(String id); // �Ǹ����� ��ǰ ���������� �Ǹ����� ����
	public int itemcountout(String id); // �Ǹ����� ��ǰ ���������� �Ǹ����� ����
	
	public List<ProductDTO> list(HashMap map); // ��ǰ��� ��ü ������
	public List<ProductDTO> listout(HashMap map); // �Ǹ����� ��ǰ���� ���̴� ������ 
	public List<CusOrderDTO> cus_order (String id); // ������� �ڵ� ��������
	public int member_status(String id); // ��ǰ��Ͽ��� �������� ǥ��� ��޿����� ������ ( 2001 2002 2003 2004 �̳��´�)
	
	//ȸ�� ����� �ٲٴ� �ڵ�
	public int cuscheck(ProductDTO productdto);//�켱 ������ ���� �ִ��� ã�´� ������ 1��  ������ 0	
	public void gijon(ProductDTO productdto); // ������ ���� �־ 1�� ������ ������ ���� status 2(�ǸŴ��)�� �ٲ۴�
	public void gijonCoNum(ProductDTO productdto); // ������ ������ �ִ� cus_order�� co_p_num��  null�� �ٲ۴�
	public void conumchange(ProductDTO productdto); // �������, ����� ���  �Ǹ������� �����  CUS_ORDER ��ȣ���� null��	
	public void statusChange(ProductDTO productdto); //product�� 0, 1, 2, 3 ���� ���º���	�������ͽ� �� ����
	public void cus_num(ProductDTO productdto); //cus oder ������� �ڵ� ��ȣ�� �����ϴ°�
	public void cus_numdelete(ProductDTO productdto); //cus oder ������� �ڵ� ��ȣ�� �����ϴ°�
	
	//�Ʒ��δ� ��ǰ ����
	public ProductDTO lister(int p_num); // ��ȣ�� �´� ��ǰ ���� ��������
	public PDetailDTO listerPD(int p_num); // ��ȣ�� �´� ��ǰ�� ���� ��������
	public void itemUP(ProductDTO productdto); //��ǰ ��������
	public void itemDpUP(PDetailDTO pdetaildto); //��ǰ�� ��������
	
	//�����Ȳ	
	public List<ProductDTO> stocklist(HashMap map);  // ���̵� �´� ��ǰ ���� �� ��� ��������
	public List<ProductDTO> stockonlist(HashMap map);  // id�� �´»�ǰ ���� �� �Ǹ����� ����Ʈ ��������
	public void stockPro (PDetailDTO pdetaildto); // ��ȣ�� �´� ��ǰ ��� ����
	public int stockcount (String id); // ������ ��ü��ǰ 
	public int stocklistcount(HashMap map);
	
	//�������
	public int listPayCount (String id); // ǰ��Ȯ�� ������� ���� 
	public int listpaynowcount (String id); // ǰ��Ȯ�� ������� ���� 
	public int powerPayCount (String id); // �Ŀ���ũ ������� Ƚ��	
	public List<CusOrderDTO> powerlist (String id); // �������� ��� ����Ʈ�� ���� ��������
	public List<CusOrderDTO> powerlistOne (HashMap map); // �������� ��� ����Ʈ�� ���� ��������
	public List<CusOrderDTO> paylist (String id); // �������� ��� ����Ʈ�� ���� ��������
	public List<CusOrderDTO> paylistTwo (HashMap map); // �������� ��� ����Ʈ�� ���� ��������
	
	//��������
	public int countter (String id); // �Ǹ����̳� ���� �������� ���ϰ� �ִ� ��ǰ ����
    public List<ProductDTO> poweredlist(String id); // �Ǹ����̳� ���� �������� ���ϰ� �ִ� ��ǰ 
    public ProductDTO payMentItem(ProductDTO productdto); // ���� ��ȣ�� �´� ��ǰ ���� ��������
    public void payFinish(CusOrderDTO cusorderDTO); //�Ŀ���ũ ���� ���������� �����Ҹ�� �����ֱ�
    public void itempayFinish(CusOrderDTO cusorderDTO);//ǰ�� Ȯ�� ���� �Ϸ�
    
    // ����� ���� ȭ��
    public int getProductTotalmoney(@Param ("check") int check, @Param ("id") String id); // �� ����� ������ �ѱ��
    public int getTotalCount(@Param ("check") int check, @Param ("id") String id); // �ѱ��ż��� �ѱ��
    public int getDeliveryPay(@Param ("check") int check, @Param ("id") String id); // �ѹ�ۺ� ���ϱ�
    public int getCoponPay(@Param ("check") int check, @Param ("id") String id); // �� ���� ����� ���ϱ� (�̶�, �����ڰ� �� ������ ��뿡�� ����)
    public Integer getHOT(@Param ("check") int check, @Param ("id") String id); // �Ǹŷ� ���� �� ���ϱ�
    
    
    //�Ǹŵ� ��ǰ ����Ʈ �̱�  
    public List<ProductMorderDTO> ProfitItemlist(HashMap map);  // �Ǹŵ� ��ǰ ��¥ �̼��� Ȯ��
    public int ProfitItemcount(@Param ("check") int check, @Param ("id") String id); //���� ���� ���� Ƚ�� 
    
    
    //���� ����ȸ������
    public int getConsumerCount (@Param ("check") int check, @Param ("id") String id); //���� ������ ȸ�� ī��Ʈ
    public List<PurchaseMemberListDTO> memberlist(HashMap map); //������ ȸ���� ������ ���°�
    public int companynum(String id); //����� �Ǻ���ȣ�޴°�
    public List<ProductDTO> itemList(String id); //�Ǹ��� �ǸŸ�� ����
    public void cusCouponPro(CouponDTO coupondto); //���������ϴ� ��
    public int userCouponCount(int companynum);  //���� ������ ����
    public List<CouponDTO> couponList(HashMap map); //������ ������ ����Ʈ
    
    // �����Ȳ �з�
    public int deliverOutCount(@Param ("check") int check, @Param ("id") String id);//��Ұ� ī��Ʈ
    public List<PurchaseMemberListDTO> deliverOutList(HashMap map); // ��ҵȰǵ��� ����Ʈ�� ������
    public int getco_num();
    
}