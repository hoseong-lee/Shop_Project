package ShopView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.scene.image.*;

public class BasketController extends Controller implements Initializable{
	ICommonService comServ;
	static private Parent root;

	ActionListener ac = new ActionHandler();
	ActionListener ac2 = new ActionHandler2();
	
	Label label;
	Label label2;
	Label label3;
	
	Button button;
	Button button2;


@Override
public void initialize(URL location, ResourceBundle resources) {
	
}
class ActionHandler implements ActionListener{

	  public void actionPerformed(ActionEvent e){
		  
JOptionPane.showMessageDialog(null, "주문 완료 되었습니다.", "감사합니다 ^^", JOptionPane.INFORMATION_MESSAGE);
	   System.out.println("장바구니 추가 완료되었습니다.");

	  }
}
	  class ActionHandler2 implements ActionListener{

		  public void actionPerformed(ActionEvent e){
		
	
			  label.setVisible(false);
			  label2.setVisible(false);
			  label3.setVisible(false);
			  button.setVisible(false);
			  button2.setVisible(false);
		  }
	  }
	private void Alert(String string) {
		
	}

	
@Override
public void setRoot(Parent root) {
	this.root= root;
}
public Popup SetCartInfo(ArrayList<String> info) {
	comServ = new CommonServiceImpl();
	System.out.println("info.get(0) : "+info.get(0));

	label = new Label();
	label2 = new Label();
	label3 = new Label();

	button = new Button();
	button2 = new Button();
	HBox frame = new HBox();
	ImageView image = new ImageView(new Image(info.get(5)));
	image.setFitHeight(100);
	image.setFitWidth(100);
	System.out.println("/"+info.get(5));
	
	label3.setGraphic(image);
	label.setText("      " + info.get(0)+
			"                "
			+info.get(1)+"                "
			+info.get(2)+"                "
			+info.get(3)+"                "
			+info.get(4)+"                ");
	label.setLayoutX(100);
	label.setLayoutY(200);
	
	button.setText("구매하기");
	button2.setText("삭제하기");
	button.setStyle("-fx-background-color : gray");
	button2.setStyle("-fx-background-color : gray");
	
	frame.getChildren().addAll(label3,label2, label,button, button2);
	frame.setAlignment(Pos.CENTER);
		
	
	Popup basketpopup;
	basketpopup = comServ.showPopUp(root.getScene(), "장바구니", frame, "None");
	if(basketpopup.isShowing())
	{basketpopup.hide();
	}
	return basketpopup;
	
}


	

       
}

	
