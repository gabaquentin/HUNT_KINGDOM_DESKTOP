<?PHP
class participantC {
	function ajouterparticipant($participant){
		$sql="insert into participants (id_evenement,id_participant) values (:id_evenement,:id_participant)";
		$db = config::getConnexion();
		try{
        $req=$db->prepare($sql);
		
        $id_evenement=$participant->getid_evenement();
        $id_participant=$participant->getid_participant();
        $req->bindValue(':id_evenement',$id_evenement);
		$req->bindValue(':id_participant',$id_participant);	
            $req->execute();
           
        }
        catch (Exception $e){
            echo 'Erreur: '.$e->getMessage();
        }
		
	}
	
	function afficherparticipant(){
		//$sql="SElECT * From participant e inner join formationphp.participant a on e.cin= a.cin";
		$sql="SElECT * From participants";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }	
	}
	function supprimerparticipant($participant,$evenement){
		$sql="DELETE FROM participants where id_participant= :participant and id_evenement= :evenement";
		$db = config::getConnexion();
        $req=$db->prepare($sql);
		$req->bindValue(':participant',$participant);
		$req->bindValue(':evenement',$evenement);
		try{
            $req->execute();
           // header('Location: index.php');
        }
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
	
	function recupererparticipant($participant,$evenement){
		$sql="SELECT * from participants where id_participant= $participant and id_evenement= $evenement";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
	
	function rechercherListeparticipants($tarif){
		$sql="SELECT * from participants where tarifHoraire=$tarif";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
}

?>