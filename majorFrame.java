package kml;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.JFileChooser;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;


public class majorFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final Frame major = new Frame("KML转换器");
		major.setLayout(new FlowLayout(FlowLayout.CENTER));
		major.setBounds(400, 300, 300, 200);
		major.setBackground(new Color(255,255,255));
		major.setResizable(false);
		
		JPanel jPanel = new JPanel();//创建jPanel 
		jPanel.setBackground(new Color(255,255,255) );
    	jPanel.add(new JLabel("<html><br/>格式提示：<br/>每一行的数据属性为：名称，经度，纬度，高程。<br/>每一个数据之间用英文逗号隔开。<br/><br/></html>" ));  //为jPanel添加JLabel 
		major.add(jPanel);
		
		JButton btn1 = new JButton("输入");
		btn1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0)  {
				
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
				jfc.showDialog(new JLabel(), "选择文件");
				File InputFile = jfc.getSelectedFile();
				if(InputFile == null){
					return;
				}
				System.out.println("输入文件："+InputFile);
				
				try {
					ConvertKML(InputFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btn2 = new JButton("退出");
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				major.setVisible(false);
				System.exit(0);
			}
		});
		major.add(btn1);
		major.add(btn2);
		major.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			//System.exit(0);
			major.setVisible(false);
			major.dispose();
		   	}
		});
		major.setVisible(true);
	}
		
	
	public static  void ConvertKML(File InputFile ) throws IOException{
		
		try {
	
			
			String OutputFile = InputFile.getAbsolutePath();
			//System.out.println(OutputFile);
			String OutputFileName=InputFile.getName();
			//System.out.println(OutputFileName);
			if(OutputFile.indexOf(".")>=0)
		     {
				OutputFile = OutputFile.substring(0, OutputFile.lastIndexOf("."));
				OutputFileName=OutputFileName.substring(0, OutputFileName.lastIndexOf("."));
		     }else{
		    	return;
		     }
			OutputFile=OutputFile+".kml";
			System.out.println("输出文件："+OutputFile);
			
			//System.out.println(OutputFileName);
			
			
			 FileReader in = new FileReader(InputFile);
		     LineNumberReader reader = new LineNumberReader(in);
		     FileWriter fw = new FileWriter(OutputFile, false);
		     BufferedWriter bw = new BufferedWriter(fw);
		        
		     Placemark placemark=new Placemark();
		     bw.write(placemark.BuildHeadInformation(OutputFileName));
		     //bw.write(placemark.BuildStyle());

		     String str = "",strname="",strpoint="";
		     int lines = 0,n=0;
		     str = reader.readLine();
		     
		     while (str != null) {
		            lines++;
		            n=str.indexOf(",");
		    	    strname=str.substring(0, n);
		    	    strpoint=str.substring(n+1);
		    	    placemark.Setname(strname);
		    	    placemark.SetPoint(strpoint);
		    	        	
		    	    bw.write(placemark.ConvertPlacemark());
		    				            
		            str = reader.readLine();

		        }
		        
		    bw.write(placemark.BuildEndInformation());
		    reader.close();
		    in.close();
		    bw.close();
		    fw.close();
		    System.out.println("已经转换完成 "+lines+"个点！");
				//TipFrame tf = new TipFrame();
				//tf.tishifu("已经转换完成 "+lines+"个点！");
			 		
		} catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
	
	
	
}
