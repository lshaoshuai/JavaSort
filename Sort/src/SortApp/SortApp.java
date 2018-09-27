package SortApp;

import radom.*; //�������
import sort.*;
import interfaces.Sort;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.animation.KeyFrame; //�ؼ�֡
import javafx.animation.Timeline; //ʱ����
import javafx.application.Application; //Ӧ�ó�����չ��Ӧ�ó�����
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;//����ͼ�����нڵ�Ļ���
import javafx.scene.Scene; //�������ǳ���ͼ���������ݵ�����
import javafx.scene.chart.BarChart; //����ͼ��
import javafx.scene.chart.CategoryAxis; //������ʵ�ֵ��ַ�����
import javafx.scene.chart.NumberAxis; //һ������
import javafx.scene.chart.XYChart; //����������ͼ��ͼ����ฺ��������ͼ������
import javafx.scene.text.Text; //�ı����
import javafx.scene.control.*;
import javafx.scene.control.ComboBox; //��ϲ˵���
import javafx.scene.control.Label;   //��ǩ��
import javafx.scene.control.Button; //��ť��
import javafx.scene.layout.AnchorPane; //AnchorPane���ְ���
import javafx.stage.Stage; //����javaFX����
import javafx.util.Duration;  //����ʱ�䳤�ȵ���
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortApp extends Application {

	private int[] movedata=new int[7];
	private int[] compdata=new int[7];
	private int[] currentdata; //��ǰ����
	private int[] data; //����data����
	public XYChart.Series series,mseries,cseries; //����һ������ϵ��
	private BarChart<String,Number> barChart,rbarChart; //����������
	private XYChart.Data[] seriesData,mseriesData,cseriesData;  //���嵥Ԫ����
	private Timeline timeline; //����ʱ����
	private int sortnum; //����ѡ��
	private int m,n,selectnum;
	private int i,j,k;
	public SortApp()  //��ʼ����������
	{
		selectnum = 0;
		
		GetArray();  //ȡ�����
		
		data = Arrays.copyOf(currentdata, currentdata.length);
		
		series = new XYChart.Series();
		seriesData = new XYChart.Data[data.length];  
		
		for (int i = 0; i < seriesData.length; i++) //��ʼ������ͼ����
		{
            seriesData[i] = new XYChart.Data(""+(i + 1), data[i]);
            series.getData().add(seriesData[i]);
        }
		
		timeline = new Timeline();
		
		m=0;
		n=0;
		
		i=0;
		j=0;
		k=0;
	}
	/**���������ú���**/
	public BarChart<String,Number> barchartcreate() {
		CategoryAxis Xline = new CategoryAxis(); //����x��
		NumberAxis Yline = new NumberAxis(0,300,20); //����Y�᳤��
		BarChart<String,Number> bar =  new BarChart<>(Xline,Yline);
		bar.setBarGap(0.0); //����ͬһ��֮��ļ��
	    bar.setCategoryGap(0.0); //��ͬ���֮��ļ��
	    bar.setAnimated(false); //���ݱ仯ʱ�����ű仯����
	    bar.setVerticalGridLinesVisible(false);  //����ʾ��ֱ����
	    bar.setHorizontalGridLinesVisible(false);
	    bar.setLegendVisible(false); //����ʾ��ɫ��ʾ
	    Yline.setLabel("value"); //����Y��ı�ǩ
	    Xline.setLabel("index"); //����X��ı�ǩ
		return bar;
	}
	private Parent Content(){
		
		
		 /*ʹ��AnchoePane����*/
		 AnchorPane anchorPane = new AnchorPane(); 
		 anchorPane.setPadding(new Insets(20.0, 20.0, 20.0, 20.0)); //�����ڱ߾�
		 
		 /*�˵�����*/
		 MenuBar menuBar = new MenuBar(); 
		 MenuItem CheckMenuItem = new MenuItem("Check Result");
		 MenuItem exitMenuItem = new MenuItem("Exit");
		 exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		 //JavaFX�߳�����������һ���µ�JavaFX�߳�
		 CheckMenuItem.setOnAction(actionEvent ->  {
			 
			    Stage secondWindow=new Stage();
		        AnchorPane root1=new AnchorPane();
		        CategoryAxis Xline = new CategoryAxis(); //����x��
				NumberAxis Yline = new NumberAxis(0,30000,500); //����Y��
				rbarChart =  new BarChart<>(Xline,Yline);
				rbarChart.setBarGap(0.0); //����ͬһ��֮��ļ��
				rbarChart.setCategoryGap(0.0); //��ͬ���֮��ļ��
				rbarChart.setAnimated(true); //���ݱ仯ʱ�����ű仯����
				rbarChart.setVerticalGridLinesVisible(false);  //����ʾ��ֱ����
				rbarChart.setLegendVisible(true); //����ʾ��ɫ��ʾ
			    Yline.setLabel("value"); //����Y��ı�ǩ
			    Xline.setLabel("varity"); //����X��ı�ǩ
		        root1.getChildren().addAll(rbarChart);
		        mseries = new XYChart.Series();
				mseriesData = new XYChart.Data[movedata.length];  
				cseries = new XYChart.Series();
				cseriesData = new XYChart.Data[compdata.length]; 
				mseries.setName("�ƶ�����");
				cseries.setName("�Ƚϴ���");
				for (int i = 0; i < mseriesData.length; i++) //��ʼ������ͼ����
				{
		            mseriesData[i] = new XYChart.Data(Select(i), 0);
		            mseries.getData().add(mseriesData[i]);
		        }
				for (int i = 0; i < cseriesData.length; i++) //��ʼ������ͼ����
				{
		            cseriesData[i] = new XYChart.Data(Select(i), 0);
		            cseries.getData().add(cseriesData[i]);
		        }
				
				timeline.getKeyFrames().clear();
				
				timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), event -> {

                      n = 0;
	                for (XYChart.Series<String, Number> s : rbarChart.getData()) {
	                	
	                    m = 0;
	                   
	                    for (XYChart.Data<String, Number> d : s.getData()) {
	                    	if(n==0)
	                    	{
	                    		d.setYValue(movedata[m]);
	                    	}
	                    	else
	                    	{
	                    		d.setYValue(compdata[m]);
	                    	}
	                        m++;
	                    }
	                    n++;
	                }

	            }));	
	            timeline.setCycleCount(1);
	            timeline.play();
	            rbarChart.getData().addAll(mseries,cseries);
	            
		        Scene scene=new Scene(root1,600,400);
		        secondWindow.setTitle("������Խ��");
		        secondWindow.setScene(scene);
		        secondWindow.show();
		        
		        
		       });
		 menuBar.prefWidthProperty().bind(anchorPane.widthProperty());
		 Menu menu = new Menu("function");
		 menu.getItems().addAll(CheckMenuItem,exitMenuItem);
		 menuBar.getMenus().add(menu);
			
			
			
		 /*��ǩ����*/
		 Label label = new Label("�ƶ�����:");
		 AnchorPane.setTopAnchor(label,20.0); //��ǩ�붥�ߵ�ƫ����
		 AnchorPane.setLeftAnchor(label,0.0); //��ǩ����ߵ�ƫ����
		 Label label2 = new Label("�Ƚϴ���:");
		 AnchorPane.setTopAnchor(label2,20.0); //��ǩ�붥�ߵ�ƫ����
		 AnchorPane.setLeftAnchor(label2,150.0); //��ǩ����ߵ�ƫ����
		 
		 /*�ı�����*/
		 Text movetext = new Text(30,10,""+i);
		 AnchorPane.setTopAnchor(movetext,22.0); //��ǩ�붥�ߵ�ƫ����
		 AnchorPane.setLeftAnchor(movetext,54.0); //��ǩ����ߵ�ƫ����
		 Text comptext = new Text(30,10,""+i);
		 AnchorPane.setTopAnchor(comptext,22.0); //��ǩ�붥�ߵ�ƫ����
		 AnchorPane.setLeftAnchor(comptext,204.0); //��ǩ����ߵ�ƫ����
		 
		 barChart = barchartcreate();
		 
		 AnchorPane.setTopAnchor(barChart,50.0); //�붥�ߵ�ƫ����
		 AnchorPane.setLeftAnchor(barChart,0.0); //����ߵ�ƫ���� 
		
		 barChart.getData().add(series); //������ϵ�м��뵽�����ᵱ��

		 
		 
		 
		 /*�㷨��Ͽ�*/
		 ObservableList<String> options = FXCollections.observableArrayList(
	                "ð������",
	                "��������",
	                "��������",
	                "ѡ������",
	                "������",
	                "ϣ������",
	                "�鲢����");
	        ComboBox<String> comboBox = new ComboBox<>(options);
	        comboBox.setPrefWidth(120.0);
	        AnchorPane.setTopAnchor(comboBox, 20.0);
	        AnchorPane.setRightAnchor(comboBox, 0.0);
	        comboBox.getSelectionModel().select(0);
	        comboBox.setOnAction(event -> {
	            sortnum = comboBox.getSelectionModel().getSelectedIndex();  //��ȡѡ�����Ϣ
	        });
	        
	      /*����ѡ��ť*/
	      ToggleGroup group = new ToggleGroup();
	      RadioButton button1 = new RadioButton("��������");
	      button1.setUserData("��������");
	      AnchorPane.setTopAnchor(button1,250.0); //��ǩ�붥�ߵ�ƫ����
	      AnchorPane.setRightAnchor(button1,45.0);
	      button1.setToggleGroup(group);
	      button1.setSelected(true);
	      RadioButton button2 = new RadioButton("��������");
	      button2.setUserData("��������");
	      AnchorPane.setTopAnchor(button2,300.0); //��ǩ�붥�ߵ�ƫ����
	      AnchorPane.setRightAnchor(button2,45.0);
	      button2.setToggleGroup(group);
	      RadioButton button3 = new RadioButton("�������");
	      button3.setUserData("�������");
	      AnchorPane.setTopAnchor(button3,350.0); //��ǩ�붥�ߵ�ƫ����
	      AnchorPane.setRightAnchor(button3,45.0);
	      button3.setToggleGroup(group);
	      
	      group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
	            if ("��������".equals(newValue.getUserData().toString())) {
	                selectnum = 0;
	            } 
	            else if("��������".equals(newValue.getUserData().toString()))
	            {
	                selectnum = 1;
	            }
	            else
	            {
	            	selectnum = 2;
	            }
	        });
		 /*��ť����*/
		 Button resetbtn = new Button();
		 resetbtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     resetbtn.setText("��������");
	     AnchorPane.setBottomAnchor(resetbtn, 0.0);
	     AnchorPane.setLeftAnchor(resetbtn, 80.0);
	     resetbtn.setOnAction(event -> {
	    	    i = 0;
	    	    movetext.setText("" + i);
	            comptext.setText("" + i);
	            data = Arrays.copyOf(currentdata, currentdata.length);

	            clearData();

	            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), event1 -> {


	                for (XYChart.Series<String, Number> s : barChart.getData()) {
	                    m = 0;
	                    for (XYChart.Data<String, Number> d : s.getData()) {
	                        d.setYValue(data[m]);
	                        m++;
	                    }
	                }

	            }));
	           

	            timeline.setCycleCount(1);
	            timeline.play();
	        });
	     
	     
	     Button changebtn = new Button();
		 changebtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     changebtn.setText("�任����");
	     AnchorPane.setBottomAnchor(changebtn, 0.0);
	     AnchorPane.setLeftAnchor(changebtn, 240.0);
	     
	     changebtn.setOnAction(event -> {
	    	 
	    	    clearData(); //����ؼ�֡
	    	    
	    	    barChart.getData().clear(); //�������ͼ�е�����
	    	    
	    	    GetArray();
	    	    
	    	    data = Arrays.copyOf(currentdata, currentdata.length);
	    	    
	    	    seriesData = new XYChart.Data[data.length]; 
	    	    
	    	    series = new XYChart.Series<>();
	    	    for (int i = 0; i < seriesData.length; i++) //������������ͼ����
	    		{
	                seriesData[i] = new XYChart.Data<>("" + (i+1), data[i]);
	                series.getData().add(seriesData[i]);
	            }
	   		
	   		    barChart.getData().add(series); //������ϵ�м��뵽�����ᵱ��
	   		    
	            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5), event1-> {
	            for (XYChart.Series<String, Number> s : barChart.getData()) {
	         	   m=0;
	               for (XYChart.Data<String, Number> d : s.getData()) {
	            	 
	                   d.setYValue(data[m]);  
	                   m++;
	              }
	             
	             }
	         
	            }));
	         
	            timeline.setCycleCount(1); //����cycleʹ�ö�������ѭ������

	            timeline.play(); //ʱ���ᶯ����ʾ*/
	            
		     });
	     
	     
	     
	     
	     Button sortbtn = new Button();
		 sortbtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     sortbtn.setText("��ʼ����");
	     AnchorPane.setBottomAnchor(sortbtn, 0.0);
	     AnchorPane.setLeftAnchor(sortbtn, 400.0);
	     /*�����ť��ʼ����*/
	     sortbtn.setOnAction(event -> {

	    	clearData();
	    	
	    	SortChoice();
	    	
	    	movedata[sortnum]=SortAdapter.getmovenum();
	    	compdata[sortnum]=SortAdapter.getcompnum();
	    	
            i=0;
            j=0;
            k=0;
            movetext.setText("" + i);
            comptext.setText("" + i);
            
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5), event1-> {
            	
            movetext.setText("" + DataUtils.mData.get(k));
            comptext.setText("" + DataUtils.cData.get(k));
            
            for (XYChart.Series<String, Number> s : barChart.getData()) {
              
               j=0;
               
               for (XYChart.Data<String, Number> d : s.getData()) {
            	   
                   d.setYValue(DataUtils.sData.get(i)[j]);  //ȡ����i������ĵ�j����
                   
                   j++;
  
              }
               i++;
             }
            k++;  
            }));
           
            timeline.setCycleCount(DataUtils.sData.size()); //����cycleѭ������ʹ�ö�������ѭ������(����Ϊһ���б�Ĵ�С)

            timeline.play(); //ʱ���ᶯ����ʾ*/
             
            

	     });

	     /*Button autobtn = new Button();
		 autobtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     autobtn.setText("�Զ�����");
	     AnchorPane.setBottomAnchor(autobtn, 0.0);
	     AnchorPane.setLeftAnchor(autobtn, 480.0);
	     autobtn.setOnAction(event -> {

		    	 clearData();
		    	  
		    	 barChart.getData().clear(); //�������ͼ�е�����

		    	 for (int i = 0; i < seriesData.length; i++) //������������ͼ����
		         {
		               seriesData[i] = new XYChart.Data<>("" + (i+1), data[i]);
		               series.getData().add(seriesData[i]);
		         }
		    	 
		    	 barChart.getData().add(series); //������ϵ�м��뵽�����ᵱ��
		    	 
		    	 SortChoice(n);
		    	 
	            i=0;
	            j=0;
	            k=0;
	            movetext.setText("" + i);
	            comptext.setText("" + i);
	            
	            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5), event1-> {
	            	
	            movetext.setText("" + DataUtils.mData.get(k));
	            comptext.setText("" + DataUtils.cData.get(k));
	            for (XYChart.Series<String, Number> s : barChart.getData()) {
	              
	               j=0;
	               
	               for (XYChart.Data<String, Number> d : s.getData()) {
	            	   
	            	   
	                   d.setYValue(DataUtils.sData.get(i)[j]);  //ȡ����i������ĵ�j����
	                   
	                   j++;
	  
	              }
	               i++;
	             }
	            k++;  
	            }));
	           
	            timeline.setCycleCount(DataUtils.sData.size()); //����cycleѭ������ʹ�ö�������ѭ������(����Ϊһ���б�Ĵ�С)

	            timeline.play(); //ʱ���ᶯ����ʾ
		     });*/  
	     

		 anchorPane.getChildren().addAll(barChart,label,label2,comboBox,sortbtn,changebtn,resetbtn,movetext,comptext,menuBar,button1,button2,button3); //�������������ӵ������ֵ���
		 return anchorPane;
		 
	}
	/**Application�Ľӿ�**/
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene root = new Scene(Content(), 650, 530);
        primaryStage.setFullScreen(false); //�Ƿ�ȫ��
        primaryStage.setResizable(false); //�ɷ�������ڴ�С
        primaryStage.setTitle("�����㷨�ڲ����ܲ���");
        primaryStage.setScene(root);
        primaryStage.show();

	}
	
	
	
	/**������**/
	public static void main(String[] args){
		launch(args);
	}
	
	/**����ؼ�֡�������б�����**/
    private void clearData() {

    	DataUtils.clear();
        timeline.getKeyFrames().clear();

    }
    void GetArray()
    {
    	if(selectnum==0)
    	{
    		currentdata = setradom.createpositiveData();
    	}
    	else if(selectnum==1)
    	{
    		currentdata = setradom.createinvertedData();
    		
    	}
    	else
    	{
    		currentdata = setradom.createRadomData();
    	}
    }
    
    void SortChoice() {
    	
    	Sort sort;
    	
    	switch(sortnum)
    	{
    	   case 0:
    		   sort = new BubbleSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 1:
    		   sort = new InsertSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 2:
    		   sort = new QuickSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 3:
    		   sort = new SelectSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 4:
    		   sort = new HeapSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 5:
    		   sort = new ShellSort();
    		   sort.sort(data);
    		   break;
    		   
    		
    	   case 6: 
    		   sort = new MergSort();
    		   sort.sort(data);
    		   break;
    	}
    }  
        void SortChoice(int n) {
    	
    	Sort sort;
    	
    	switch(n)
    	{
    	   case 0:
    		   sort = new BubbleSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 1:
    		   sort = new InsertSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 2:
    		   sort = new QuickSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 3:
    		   sort = new SelectSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 4:
    		   sort = new HeapSort();
    		   sort.sort(data);
    		   break;
    		   
    	   case 5:
    		   sort = new ShellSort();
    		   sort.sort(data);
    		   break;
    		   
    		
    	   case 6: 
    		   sort = new MergSort();
    		   sort.sort(data);
    		   break;
    	}
    }
    String Select(int n)
    {
        String string=new String();
    	switch(n)
    	{
    	   case 0:
    		   string =new String("Bubble");
    		   break;
    		   
    	   case 1:
    		   string =new String("Insert");
    		   break;
    		   
    	   case 2:
    		   string =new String("	Quick");
    		   break;
    		   
    	   case 3:
    		   string =new String("Select");
    		   break;
    		   
    	   case 4:
    		   string =new String("Heap");
    		   break;
    		   
    	   case 5:
    		   string =new String("Shell");
    		   break;
    		   
    		
    	   case 6: 
    		   string =new String("Merge");
    		   break;
    	}
    	return string;
    }

}

