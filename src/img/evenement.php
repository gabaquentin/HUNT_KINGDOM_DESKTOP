<?PHP
class evenement{
	private $titre;
	private $image;
	private $description;
	private $date_fin;
	private $type;
	private $places;
	private $lien;
	function __construct($titre,$image,$description,$date_fin,$type,$places,$lien){
	  
		$this->titre=$titre;
		$this->image=$image;
		$this->description=$description;
		$this->date_fin=$date_fin;		
		$this->type=$type;		
		$this->places=$places;
		$this->lien=$lien;
	}

	
	function gettitre(){
		return $this->titre;
	}
	function getimage(){
		return $this->image;
	}
	function getdescription(){
		return $this->description;
	}
	function getdate_fin(){
		return $this->date_fin;
	}
	function gettype(){
		return $this->type;
	}
	function getplaces(){
		return $this->places;
	}
	function getlien(){
		return $this->lien;
	}
}

?>