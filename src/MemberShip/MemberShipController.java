package MemberShip;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import MemberShip.DB.IMemberShipDBManage;
import MemberShip.DB.Member;
import MemberShip.DB.MemberShipDBManageImpl;
import MemberShip.Service.IMemberShipService;
import MemberShip.Service.MemberShipServiceImpl;
import application.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;

public class MemberShipController implements Initializable{
	ICommonService comServ;
	HomeController ctrler;
	IMemberShipDBManage memberManage;
	IMemberShipService imembershipserv;
	private Parent root;
	private Popup alertPopup;
	
	final int ID = 0;
	final int NAME = 1;
	final int PW = 2;
	final int PWRE = 3;
	final int PHONE = 4;
	final int EMAIL = 5;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ = new CommonServiceImpl();
		ctrler = new HomeController();
		memberManage = new MemberShipDBManageImpl();
		imembershipserv = new MemberShipServiceImpl();
	}
	
	private boolean isCheck(Map<String, TextField> txtFldMap, String []txtFldIdArr) {
		alertPopup = new Popup();
		if(comServ.isEmpty(txtFldMap, txtFldIdArr)) {
			
			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setPadding(new Insets(10));
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl1 = new Label("회원가입 실패");
			txtlbl1.setPrefSize(250, 100);
			txtlbl1.setFont(new Font(36));
			txtlbl1.setAlignment(Pos.CENTER);
			Label txtlbl2 = new Label("빈칸을 입력해주세요");
			txtlbl2.setPrefSize(400, 100);
			txtlbl2.setFont(new Font(24));
			txtlbl2.setAlignment(Pos.CENTER);
			Button failBtn = new Button("확인");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
			stackpane.getChildren().add(vbox);
			alertPopup = comServ.showAlertPopUp(root.getScene(),"실패", stackpane);
			return false;
		}
		String pw = txtFldMap.get(txtFldIdArr[PW]).getText();
		String pwre = txtFldMap.get(txtFldIdArr[PWRE]).getText();
		
		
		  if(!(imembershipserv.comparePW(pw, pwre))) { 
			  
				StackPane stackpane = new StackPane();
				VBox vbox = new VBox();
				vbox.setPadding(new Insets(10));
				vbox.setAlignment(Pos.CENTER);
				Label txtlbl1 = new Label("회원가입 실패");
				txtlbl1.setPrefSize(250, 100);
				txtlbl1.setFont(new Font(36));
				txtlbl1.setAlignment(Pos.CENTER);
				Label txtlbl2 = new Label("패스워드가 다릅니다.");
				txtlbl2.setPrefSize(400, 100);
				txtlbl2.setFont(new Font(24));
				txtlbl2.setAlignment(Pos.CENTER);
				Button failBtn = new Button("확인");
				failBtn.setFont(new Font(24));
				failBtn.setOnAction(event->{
					Parent poproot = comServ.getScene(event);
					Popup popscene = (Popup)poproot.getScene().getWindow();
					popscene.hide();
				});
				vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
				stackpane.getChildren().add(vbox);
				alertPopup = comServ.showAlertPopUp(root.getScene(),"실패", stackpane);
			  
			  return false; }
		
		
		return true;
		
	}
	public void CancelProc(ActionEvent e) {
		ctrler.CancleBtn1();
	}
	public void MemberShipProc(ActionEvent e) {// 회원정보 데이터를 membershipDB에 입력
		this.root = comServ.getScene(e);
		String []txtFldIdArr = {"#IdTxt", "#NameTxt", "#PwTxt","#PwTxtre", "#PhoneNumberTxt","#UserEmailTxt"};
		Map<String, TextField> txtFldMap = 
				comServ.getTextFieldInfo(root, txtFldIdArr);
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		Member member = new Member();
		member.setId(txtFldMap.get(txtFldIdArr[ID]).getText());
		member.setName((txtFldMap.get(txtFldIdArr[NAME]).getText()));
		member.setPw((txtFldMap.get(txtFldIdArr[PW]).getText()));
		member.setPhone((txtFldMap.get(txtFldIdArr[PHONE]).getText()));
		member.setEmail((txtFldMap.get(txtFldIdArr[EMAIL]).getText()));
		
		System.out.println(txtFldMap.get(txtFldIdArr[ID]).getText());
		System.out.println((txtFldMap.get(txtFldIdArr[NAME]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[PW]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[PWRE]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[PHONE]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[EMAIL]).getText()));
		
		if(memberManage.MemberProc(member)) {
		
		StackPane stackpane = new StackPane();
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		Label txtlbl = new Label("회원가입을 성공하셨습니다.");
		txtlbl.setPrefSize(600, 100);
		txtlbl.setFont(new Font(36));
		txtlbl.setAlignment(Pos.CENTER);
		Button okBtn = new Button("확인");
		okBtn.setFont(new Font(24));
		okBtn.setOnAction(event->{
			Parent poproot = comServ.getScene(event);
			Popup popscene = (Popup)poproot.getScene().getWindow();
			popscene.hide();
		});
		vbox.getChildren().addAll(txtlbl,okBtn);
		stackpane.getChildren().add(vbox);
		alertPopup = comServ.showAlertPopUp(root.getScene(),"회원가입", stackpane);
		ctrler.setRoot(root);
		ctrler.LoginView();
		}
		else {
			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setPadding(new Insets(10));
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl = new Label("회원가입에 실패하셨습니다.");
			txtlbl.setPrefSize(600, 100);
			txtlbl.setFont(new Font(36));
			txtlbl.setAlignment(Pos.CENTER);
			Button okBtn = new Button("확인");
			okBtn.setFont(new Font(24));
			okBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl,okBtn);
			stackpane.getChildren().add(vbox);
			alertPopup = comServ.showAlertPopUp(root.getScene(),"회원가입", stackpane);
		}
	}
	
		
		
}
