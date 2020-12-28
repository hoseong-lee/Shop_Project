package MemberShip.Service;

import MemberShip.DB.IMemberShipDBManage;
import MemberShip.DB.Member;
import MemberShip.DB.MemberShipDBManageImpl;

public class MemberShipServiceImpl implements IMemberShipService{
	@Override
	public boolean MemberProc(Member member) {
		IMemberShipDBManage memberManage= new MemberShipDBManageImpl();
		return memberManage.MemberProc(member);
	}
	
	
	@Override
	public boolean comparePW(String pw, String pwre) {
		/*
		 * if(!pw.contentEquals(pwOk)) return true; return false;
		 */
		return pw.contentEquals(pwre);
	}
}
