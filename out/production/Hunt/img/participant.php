<?PHP
class participant{
	private $id_participant;
	private $id_evenement;
	function __construct($id_evenement,$id_participant){
		$this->id_evenement=$id_evenement;
		$this->id_participant=$id_participant;
	}


	function getid_evenement(){
		return $this->id_evenement;
	}
	function getid_participant(){
		return $this->id_participant;
	}
	
}

?>