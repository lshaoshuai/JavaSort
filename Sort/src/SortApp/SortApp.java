package SortApp;

import radom.*; //随机函数
import sort.*;
import interfaces.Sort;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.animation.KeyFrame; //关键帧
import javafx.animation.Timeline; //时间轴
import javafx.application.Application; //应用程序扩展的应用程序类
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;//场景图中所有节点的基类
import javafx.scene.Scene; //场景类是场景图中所有内容的容器
import javafx.scene.chart.BarChart; //条形图类
import javafx.scene.chart.CategoryAxis; //在轴上实现的字符串类
import javafx.scene.chart.NumberAxis; //一个轴类
import javafx.scene.chart.XYChart; //所有连个轴图的图表基类负责绘制轴和图的内容
import javafx.scene.text.Text; //文本类库
import javafx.scene.control.*;
import javafx.scene.control.ComboBox; //组合菜单类
import javafx.scene.control.Label;   //标签类
import javafx.scene.control.Button; //按钮类
import javafx.scene.layout.AnchorPane; //AnchorPane布局版面
import javafx.stage.Stage; //顶级javaFX容器
import javafx.util.Duration;  //定义时间长度的类
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortApp extends Application {

	private int[] movedata=new int[7];
	private int[] compdata=new int[7];
	private int[] currentdata; //当前数组
	private int[] data; //定义data数组
	public XYChart.Series series,mseries,cseries; //定义一个数据系列
	private BarChart<String,Number> barChart,rbarChart; //定义坐标轴
	private XYChart.Data[] seriesData,mseriesData,cseriesData;  //定义单元数据
	private Timeline timeline; //定义时间轴
	private int sortnum; //排序选择
	private int m,n,selectnum;
	private int i,j,k;
	public SortApp()  //初始化各项数据
	{
		selectnum = 0;
		
		GetArray();  //取随机数
		
		data = Arrays.copyOf(currentdata, currentdata.length);
		
		series = new XYChart.Series();
		seriesData = new XYChart.Data[data.length];  
		
		for (int i = 0; i < seriesData.length; i++) //初始化条形图数据
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
	/**坐标轴设置函数**/
	public BarChart<String,Number> barchartcreate() {
		CategoryAxis Xline = new CategoryAxis(); //设置x轴
		NumberAxis Yline = new NumberAxis(0,300,20); //设置Y轴长度
		BarChart<String,Number> bar =  new BarChart<>(Xline,Yline);
		bar.setBarGap(0.0); //设置同一类之间的间隔
	    bar.setCategoryGap(0.0); //不同类别之间的间隔
	    bar.setAnimated(false); //数据变化时不播放变化动画
	    bar.setVerticalGridLinesVisible(false);  //不显示垂直网格
	    bar.setHorizontalGridLinesVisible(false);
	    bar.setLegendVisible(false); //不显示颜色标示
	    Yline.setLabel("value"); //设置Y轴的标签
	    Xline.setLabel("index"); //设置X轴的标签
		return bar;
	}
	private Parent Content(){
		
		
		 /*使用AnchoePane布局*/
		 AnchorPane anchorPane = new AnchorPane(); 
		 anchorPane.setPadding(new Insets(20.0, 20.0, 20.0, 20.0)); //设置内边距
		 
		 /*菜单设置*/
		 MenuBar menuBar = new MenuBar(); 
		 MenuItem CheckMenuItem = new MenuItem("Check Result");
		 MenuItem exitMenuItem = new MenuItem("Exit");
		 exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		 //JavaFX线程中另外启动一个新的JavaFX线程
		 CheckMenuItem.setOnAction(actionEvent ->  {
			 
			    Stage secondWindow=new Stage();
		        AnchorPane root1=new AnchorPane();
		        CategoryAxis Xline = new CategoryAxis(); //设置x轴
				NumberAxis Yline = new NumberAxis(0,30000,500); //设置Y轴
				rbarChart =  new BarChart<>(Xline,Yline);
				rbarChart.setBarGap(0.0); //设置同一类之间的间隔
				rbarChart.setCategoryGap(0.0); //不同类别之间的间隔
				rbarChart.setAnimated(true); //数据变化时不播放变化动画
				rbarChart.setVerticalGridLinesVisible(false);  //不显示垂直网格
				rbarChart.setLegendVisible(true); //不显示颜色标示
			    Yline.setLabel("value"); //设置Y轴的标签
			    Xline.setLabel("varity"); //设置X轴的标签
		        root1.getChildren().addAll(rbarChart);
		        mseries = new XYChart.Series();
				mseriesData = new XYChart.Data[movedata.length];  
				cseries = new XYChart.Series();
				cseriesData = new XYChart.Data[compdata.length]; 
				mseries.setName("移动次数");
				cseries.setName("比较次数");
				for (int i = 0; i < mseriesData.length; i++) //初始化条形图数据
				{
		            mseriesData[i] = new XYChart.Data(Select(i), 0);
		            mseries.getData().add(mseriesData[i]);
		        }
				for (int i = 0; i < cseriesData.length; i++) //初始化条形图数据
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
		        secondWindow.setTitle("排序测试结果");
		        secondWindow.setScene(scene);
		        secondWindow.show();
		        
		        
		       });
		 menuBar.prefWidthProperty().bind(anchorPane.widthProperty());
		 Menu menu = new Menu("function");
		 menu.getItems().addAll(CheckMenuItem,exitMenuItem);
		 menuBar.getMenus().add(menu);
			
			
			
		 /*标签设置*/
		 Label label = new Label("移动次数:");
		 AnchorPane.setTopAnchor(label,20.0); //标签与顶边的偏移量
		 AnchorPane.setLeftAnchor(label,0.0); //标签与左边的偏移量
		 Label label2 = new Label("比较次数:");
		 AnchorPane.setTopAnchor(label2,20.0); //标签与顶边的偏移量
		 AnchorPane.setLeftAnchor(label2,150.0); //标签与左边的偏移量
		 
		 /*文本设置*/
		 Text movetext = new Text(30,10,""+i);
		 AnchorPane.setTopAnchor(movetext,22.0); //标签与顶边的偏移量
		 AnchorPane.setLeftAnchor(movetext,54.0); //标签与左边的偏移量
		 Text comptext = new Text(30,10,""+i);
		 AnchorPane.setTopAnchor(comptext,22.0); //标签与顶边的偏移量
		 AnchorPane.setLeftAnchor(comptext,204.0); //标签与左边的偏移量
		 
		 barChart = barchartcreate();
		 
		 AnchorPane.setTopAnchor(barChart,50.0); //与顶边的偏移量
		 AnchorPane.setLeftAnchor(barChart,0.0); //与左边的偏移量 
		
		 barChart.getData().add(series); //将数据系列加入到坐标轴当中

		 
		 
		 
		 /*算法组合框*/
		 ObservableList<String> options = FXCollections.observableArrayList(
	                "冒泡排序",
	                "插入排序",
	                "快速排序",
	                "选择排序",
	                "堆排序",
	                "希尔排序",
	                "归并排序");
	        ComboBox<String> comboBox = new ComboBox<>(options);
	        comboBox.setPrefWidth(120.0);
	        AnchorPane.setTopAnchor(comboBox, 20.0);
	        AnchorPane.setRightAnchor(comboBox, 0.0);
	        comboBox.getSelectionModel().select(0);
	        comboBox.setOnAction(event -> {
	            sortnum = comboBox.getSelectionModel().getSelectedIndex();  //获取选择框信息
	        });
	        
	      /*设置选择按钮*/
	      ToggleGroup group = new ToggleGroup();
	      RadioButton button1 = new RadioButton("正序数列");
	      button1.setUserData("正序数列");
	      AnchorPane.setTopAnchor(button1,250.0); //标签与顶边的偏移量
	      AnchorPane.setRightAnchor(button1,45.0);
	      button1.setToggleGroup(group);
	      button1.setSelected(true);
	      RadioButton button2 = new RadioButton("倒序数列");
	      button2.setUserData("倒序数列");
	      AnchorPane.setTopAnchor(button2,300.0); //标签与顶边的偏移量
	      AnchorPane.setRightAnchor(button2,45.0);
	      button2.setToggleGroup(group);
	      RadioButton button3 = new RadioButton("随机数列");
	      button3.setUserData("随机数列");
	      AnchorPane.setTopAnchor(button3,350.0); //标签与顶边的偏移量
	      AnchorPane.setRightAnchor(button3,45.0);
	      button3.setToggleGroup(group);
	      
	      group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
	            if ("正序数列".equals(newValue.getUserData().toString())) {
	                selectnum = 0;
	            } 
	            else if("倒序数列".equals(newValue.getUserData().toString()))
	            {
	                selectnum = 1;
	            }
	            else
	            {
	            	selectnum = 2;
	            }
	        });
		 /*按钮设置*/
		 Button resetbtn = new Button();
		 resetbtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     resetbtn.setText("重置数据");
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
	     changebtn.setText("变换数据");
	     AnchorPane.setBottomAnchor(changebtn, 0.0);
	     AnchorPane.setLeftAnchor(changebtn, 240.0);
	     
	     changebtn.setOnAction(event -> {
	    	 
	    	    clearData(); //清除关键帧
	    	    
	    	    barChart.getData().clear(); //清除条形图中的数据
	    	    
	    	    GetArray();
	    	    
	    	    data = Arrays.copyOf(currentdata, currentdata.length);
	    	    
	    	    seriesData = new XYChart.Data[data.length]; 
	    	    
	    	    series = new XYChart.Series<>();
	    	    for (int i = 0; i < seriesData.length; i++) //重新设置条形图数据
	    		{
	                seriesData[i] = new XYChart.Data<>("" + (i+1), data[i]);
	                series.getData().add(seriesData[i]);
	            }
	   		
	   		    barChart.getData().add(series); //将数据系列加入到坐标轴当中
	   		    
	            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5), event1-> {
	            for (XYChart.Series<String, Number> s : barChart.getData()) {
	         	   m=0;
	               for (XYChart.Data<String, Number> d : s.getData()) {
	            	 
	                   d.setYValue(data[m]);  
	                   m++;
	              }
	             
	             }
	         
	            }));
	         
	            timeline.setCycleCount(1); //设置cycle使得动画可以循环运行

	            timeline.play(); //时间轴动画演示*/
	            
		     });
	     
	     
	     
	     
	     Button sortbtn = new Button();
		 sortbtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     sortbtn.setText("开始排序");
	     AnchorPane.setBottomAnchor(sortbtn, 0.0);
	     AnchorPane.setLeftAnchor(sortbtn, 400.0);
	     /*点击按钮开始排序*/
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
            	   
                   d.setYValue(DataUtils.sData.get(i)[j]);  //取出第i个数组的第j个数
                   
                   j++;
  
              }
               i++;
             }
            k++;  
            }));
           
            timeline.setCycleCount(DataUtils.sData.size()); //设置cycle循环次数使得动画可以循环运行(次数为一个列表的大小)

            timeline.play(); //时间轴动画演示*/
             
            

	     });

	     /*Button autobtn = new Button();
		 autobtn.setStyle("-fx-font: 18 arial; -fx-base: #ee2211;");
	     autobtn.setText("自动测试");
	     AnchorPane.setBottomAnchor(autobtn, 0.0);
	     AnchorPane.setLeftAnchor(autobtn, 480.0);
	     autobtn.setOnAction(event -> {

		    	 clearData();
		    	  
		    	 barChart.getData().clear(); //清除条形图中的数据

		    	 for (int i = 0; i < seriesData.length; i++) //重新设置条形图数据
		         {
		               seriesData[i] = new XYChart.Data<>("" + (i+1), data[i]);
		               series.getData().add(seriesData[i]);
		         }
		    	 
		    	 barChart.getData().add(series); //将数据系列加入到坐标轴当中
		    	 
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
	            	   
	            	   
	                   d.setYValue(DataUtils.sData.get(i)[j]);  //取出第i个数组的第j个数
	                   
	                   j++;
	  
	              }
	               i++;
	             }
	            k++;  
	            }));
	           
	            timeline.setCycleCount(DataUtils.sData.size()); //设置cycle循环次数使得动画可以循环运行(次数为一个列表的大小)

	            timeline.play(); //时间轴动画演示
		     });*/  
	     

		 anchorPane.getChildren().addAll(barChart,label,label2,comboBox,sortbtn,changebtn,resetbtn,movetext,comptext,menuBar,button1,button2,button3); //将所有组件都添加到根布局当中
		 return anchorPane;
		 
	}
	/**Application的接口**/
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene root = new Scene(Content(), 650, 530);
        primaryStage.setFullScreen(false); //是否全屏
        primaryStage.setResizable(false); //可否调整窗口大小
        primaryStage.setTitle("排序算法内部性能测试");
        primaryStage.setScene(root);
        primaryStage.show();

	}
	
	
	
	/**主函数**/
	public static void main(String[] args){
		launch(args);
	}
	
	/**清楚关键帧数据与列表数据**/
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

