package kml;

class  Placemark {
	private String Point;
	private String name;
	private String style;
	
	public Placemark() {
		Point="";name="";style="";
	}
	public Placemark(String strpoint) {
		Point=strpoint;		name="";		style="";
	}	
	public Placemark(String strpoint,String strname) {
		Point=strpoint;		name=strname;		style="";
	}
	
	public Placemark(String strpoint,String strname,String strstyle) {
		Point=strpoint;		name=strname;		style=strstyle;
	}
	
	public String GetPoint() {
		return Point;
	}
	
	public String Getname() {
		return name;
	}
	public String Getstyle() {
		return style;
	}
	
	public void SetPoint(String strpoint) {
		Point=strpoint;	
	}
	
	public void Setname(String strname) {
		name=strname;
	}
	public void Setstyle(String strstyle) {
		style=strstyle;
	}
	
	public String ConvertPlacemark() {
		String placemark="",strpoint="",strname="",strstyle="";
		if (Point!="") {
			strpoint="\t\t<Point>\r\n"+"\t\t\t<coordinates>"+Point+"</coordinates>\r\n"+"\t\t</Point>\r\n";
			
		}
		if (name!="") {
			strname="\t\t\t<name>"+name+"</name>\r\n";
		}
		if (style!="") {
			strstyle="\t\t<styleUrl>"+style+"</styleUrl>\r\n";
		}
		placemark= "\t<Placemark>\r\n"+strstyle+strname+strpoint+"\t</Placemark>\r\n";
		
		return placemark;
		
		
	}
	public  String  BuildHeadInformation(String OutputName) {
		String head="",headversion="",headxmlns="",headname="";
		headversion="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
		headxmlns="<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\">\r\n";
		headname="\t<name>"+OutputName+"</name>\r\n";
		head=headversion+headxmlns+	"<Document>\r\n"+headname;
		return head;

        }

	public  String  BuildEndInformation() {
		return "</Document>\r\n"+"</kml>";
		
        }
	
	
	public  String  BuildStyle() {
		String style="",stylemap="";
		style="\t<Style id=\"sh_placemark_circle_highlight\">\r\n"+
				"\t\t<IconStyle>\r\n"+
				"\t\t\t<color>ff0000ff</color>\r\n"+
				"\t\t\t<scale>0.8</scale>\r\n"+
				"\t\t\t<Icon>\r\n"+
				"\t\t\t\t<href>http://maps.google.com/mapfiles/kml/shapes/open-diamond.png</href>\r\n"+
				"\t\t\t</Icon>\r\n"+
				"\t\t</IconStyle>\r\n"+
				"\t</Style>\r\n";
		stylemap="\t<StyleMap id=\"msn_placemark_circle\">\r\n"+
				"\t\t<Pair>\r\n"+
				"\t\t\t<key>normal</key>\r\n"+
				"\t\t\t<styleUrl>#sn_placemark_circle</styleUrl>\r\n"+
				"\t\t</Pair>\r\n"+
				"\t\t<Pair>\r\n"+
				"\t\t\t<key>highlight</key>\r\n"+
				"\t\t\t<styleUrl>#sh_placemark_circle_highlight</styleUrl>\r\n"+
				"\t\t</Pair>\r\n"+
				"\t</StyleMap>\r\n";
		return style+stylemap;
	
	 }
	
	
}

