package application;

import java.net.URL;
import java.util.ResourceBundle;

import BoardEx.DB.ListController;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import ShopView.ShopMainController;
import Webpage.WebController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	static ICommonService comserv;
	WebController wbctrler;
	ShopMainController smctrler;
	public static String userState = null;
	@FXML Button fullscrBtn;
	@FXML TextField userStateTxt;
	@FXML Button loginBtn;
	@FXML BorderPane mainPane;
	@FXML Label userTextLabel;
	@FXML Box homeBox;
	@FXML Box shopBox;
	@FXML Box boardBox;
	@FXML Box membershipBox;
	@FXML Box loginBox;
	@FXML Box searchBox;
	@FXML StackPane homePane;
	@FXML StackPane shopPane;
	@FXML StackPane boardPane;
	@FXML StackPane membershipPane;
	@FXML StackPane loginPane;
	@FXML StackPane searchPane;
	
	
	static private ScrollPane scrPane;
	static private Scene scene;
	static Popup mainPopup;
	static private Parent root;
	static Node mainPopupNode;
	static double mousePosX;
	static double mousePosY;
	static double mouseOldX;
	static double mouseOldY;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainPopup = new Popup();
		comserv = new CommonServiceImpl();
		wbctrler = new WebController();
		scrPane = new ScrollPane();
		smctrler = new ShopMainController();
		UserTextFieldControl(userState); 
		if(!userStateTxt.getText().contentEquals("GUEST")) {
			loginBtn.setText("LOGOUT");
		}
		mainPopupNode = comserv.Load("../application/PopUp.fxml");
		userTextLabel.setVisible(true);
		userTextLabel.setText(userState);
		System.out.println("init완료");
		homeBox.setOnMouseClicked(e->{
			HomeView();
		});
		shopBox.setOnMouseClicked(e->{
			ShopView();
		});
		boardBox.setOnMouseClicked(e->{
			BoardView();
		});
		membershipBox.setOnMouseClicked(e->{
			MembershipView();
		});
		loginBox.setOnMouseClicked(e->{
			LoginView();
		});
		searchBox.setOnMouseClicked(e->{
			SearchView();
		});
	}
	public void setRoot(Parent root) {

		this.root = root;
		System.out.println("this.root : "+ this.root);
	}
	public void setPopup(Popup popup) {
		this.mainPopup = popup;
	}
	public void MainPopupShowInit() {
		scene = root.getScene();
		if(this.mainPopup.isShowing()) {
			this.mainPopup.hide();
		}
		StackPane popupCenterPane = new StackPane();
		Box box = new Box();
		box.setDepth(200);
		box.setHeight(200);
		box.setWidth(200);
		box.setOnMouseClicked(e->{
			System.out.println("1조 제작 : 강민성(SEARCH) , 김동우(BOARD), 박준하(SHOP) , 이강희(HOME) , 이호성(MEMBERSHIP)");
		});
		popupCenterPane.getChildren().add(box);
		popupCenterPane.setPrefSize(400,400);
		box=BoxScene(popupCenterPane, box);
		mainPopup = comserv.showPopUp(scene,"팝업창1",popupCenterPane, "MainPopUpContent");
		setPopup(mainPopup);
	}
	
	public static Box BoxScene(StackPane panescene, Box box) {
	    Rotate rotateX = new Rotate(10, 0, 0, 0, Rotate.X_AXIS);
	    Rotate rotateY = new Rotate(5, 0, 0, 0, Rotate.Y_AXIS);
	    box.getTransforms().addAll(rotateX, rotateY);

	    panescene.setOnMousePressed(me -> {
	        mouseOldX = me.getSceneX();
	        mouseOldY = me.getSceneY();
	    });
	    panescene.setOnMouseDragged(me -> {
	        mousePosX = me.getSceneX();
	        mousePosY = me.getSceneY();
	        rotateX.setAngle(rotateX.getAngle() + (mousePosY - mouseOldY));
	        rotateY.setAngle(rotateY.getAngle() - (mousePosX - mouseOldX));
	        mouseOldX = mousePosX;
	        mouseOldY = mousePosY;
	    });

	    return box;
	}
	public void HomeView() {
		
		BorderPane borderPane = (BorderPane)this.root;
		borderPane.setCenter(((BorderPane)comserv.Load("../application/HomeMain.fxml")).getCenter());
		((Label)borderPane.lookup("#userTextLabel")).setText(userStateTxt.getText());
		Box homeBox = (Box)root.lookup("#homeBox");
		Box shopBox = (Box)root.lookup("#shopBox");
		Box boardBox = (Box)root.lookup("#boardBox");
		Box membershipBox = (Box)root.lookup("#membershipBox");
		Box loginBox = (Box)root.lookup("#loginBox");
		Box searchBox = (Box)root.lookup("#searchBox");
		StackPane homePane = (StackPane)root.lookup("#homePane");
		StackPane shopPane = (StackPane)root.lookup("#shopPane");
		StackPane boardPane = (StackPane)root.lookup("#boardPane");
		StackPane membershipPane = (StackPane)root.lookup("#membershipPane");
		StackPane loginPane = (StackPane)root.lookup("#loginPane");
		StackPane searchPane = (StackPane)root.lookup("#searchPane");
		homeBox = BoxScene(homePane, homeBox);
		shopBox = BoxScene(shopPane, shopBox);
		boardBox = BoxScene(boardPane, boardBox);
		membershipBox = BoxScene(membershipPane, membershipBox);
		loginBox =  BoxScene(loginPane, loginBox);
		searchBox = BoxScene(searchPane, searchBox);
		
	}
	public void UserTextFieldControl(String user) {
		if(userState==null){
			userState="GUEST";
		}
		else {
		this.userState = user;
		}
		System.out.println(userState);

		userStateTxt.setText(userState);
		userStateTxt.setStyle("-fx-background-color: null;-fx-text-fill: white;");
	}
	public void ShopView() {
		MainPopupShowInit();
		
		BorderPane borderPane = (BorderPane)this.root;
		Parent shopView = comserv.Load("../ShopView/shopView.fxml");
		borderPane.setCenter(shopView);
		ShopMainController smctrler = new ShopMainController();
		this.scrPane = (ScrollPane)borderPane.getCenter();
		setScrPane((ScrollPane)scrPane.getContent().lookup("#shopScrPane"));
		smctrler.setRoot(shopView);
		smctrler.setBoardState(2);
		System.out.println(scrPane.getId());
	}
	public void BoardView() {
		MainPopupShowInit();
		
		BorderPane borderPane = (BorderPane)this.root;
		borderPane.setLeft(null);
		borderPane.setCenter(comserv.Load("../BoardEx/DB/BoardListEx.fxml"));
		borderPane.getScene().getWindow().sizeToScene();
		ListController lstctrler =new ListController();
		lstctrler.setRoot(root);
		lstctrler.setBoardState(1);
	}
	public void MembershipView() {
		MainPopupShowInit();

		System.out.println("사용자 : " + userStateTxt.getText());
		System.out.println(userStateTxt.getText().contentEquals("GUEST"));
		if(userStateTxt.getText().contentEquals("GUEST")) {	
			LoginView();
		}
		else{
			BorderPane borderPane = (BorderPane)root;
			borderPane.setLeft(null);
			borderPane.setCenter(comserv.Load("../MembershipFxml/mypage(main).fxml"));
			return;
		}

	}
	public void LoginView() {
		MainPopupShowInit();

		BorderPane borderPane = (BorderPane)this.root;
		Parent centerScene = comserv.Load("../MembershipFxml/loginform.fxml");
		
		StackPane pane = new StackPane();
		pane.getChildren().add(centerScene);
		pane.setPrefSize(1800, 932);
		scrPane.setContent(pane);
		scrPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		borderPane.setLeft(null);
		borderPane.setCenter(scrPane);
		borderPane.setBottom(null);
		this.userStateTxt = (TextField)borderPane.lookup("#userStateTxt");
			if(!userStateTxt.getText().contentEquals("GUEST")) {
				userState = "GUEST";
				userStateTxt.setText(userState);
				loginBtn.setText("LOGIN");
			}

	}
	
	public void CancleBtn1() {
		
		MainPopupShowInit();
		BorderPane borderPane = (BorderPane)this.root;
		Parent centerScene = comserv.Load("../MembershipFxml/loginform.fxml");
		
		StackPane pane = new StackPane();
		pane.getChildren().add(centerScene);
		pane.setPrefSize(1800, 932);
		scrPane.setContent(pane);
		scrPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		borderPane.setLeft(null);
		borderPane.setCenter(scrPane);
		borderPane.setBottom(null);
		
		
	}

	public void SearchView() {
		SearchItems.Controller ctrler = new SearchItems.Controller();
		MainPopupShowInit();

		BorderPane borderPane = (BorderPane)this.root;
		borderPane.setLeft(null);
		borderPane.setBottom(null);
		Parent centerScene = comserv.Load("../SearchItems/searchwindow.fxml");
		
		borderPane.setCenter(centerScene);
		ctrler.setRoot(root);
		smctrler.setBoardState(2);
		BorderPane searchPane = (BorderPane)borderPane.getCenter();
		setScrPane((ScrollPane)searchPane.getCenter());
	}
	public void CartView(ActionEvent e) {
		MainPopupShowInit();
		
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
		wbctrler.basketView(e);

	}
	public void TopView() {
		scrPane.setVvalue(0);
	}
	public void BottomView() {
		scrPane.setVvalue(100);
	}

	public void FullScreen(ActionEvent e) {
		Stage stage = (Stage) fullscrBtn.getScene().getWindow();
		stage.setFullScreen(true);
	}
	public void setUser(String user) {
		System.out.println(user);
		UserTextFieldControl(user);
	}
	public void setScrPane(ScrollPane scrpane) {
		this.scrPane = scrpane;
	}

	

}
