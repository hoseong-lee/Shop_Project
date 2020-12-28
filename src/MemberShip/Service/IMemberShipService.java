package MemberShip.Service;

import MemberShip.DB.Member;

public interface IMemberShipService {

	public boolean MemberProc(Member member);


	public boolean comparePW(String pw, String pwre);

}
