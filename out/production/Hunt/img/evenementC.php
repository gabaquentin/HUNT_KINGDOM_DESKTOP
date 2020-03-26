<?PHP
class evenementC {
	function ajouterEvenement($evenement){
		$sql="insert into evenement (titre,image,description,date_fin,type,places,lien) values (:titre, :image,:description,:date_fin,:type,:places,:lien)";
		$db = config::getConnexion();
		try{
        $req=$db->prepare($sql);
		
        $titre=$evenement->gettitre();
        $image=$evenement->getimage();
        $description=$evenement->getdescription();
        $date_fin=$evenement->getdate_fin();        
        $type=$evenement->gettype();        
        $places=$evenement->getplaces();   
        $lien=$evenement->getlien();
		$req->bindValue(':titre',$titre);
		$req->bindValue(':image',$image);
		$req->bindValue(':description',$description);
		$req->bindValue(':date_fin',$date_fin);	
		$req->bindValue(':type',$type);		
		$req->bindValue(':places',$places);	
		$req->bindValue(':lien',$lien);		
            $req->execute();
           
        }
        catch (Exception $e){
            echo 'Erreur: '.$e->getMessage();
        }
		
	}
	
	function afficherEvenement(){
		//$sql="SElECT * From evenement e inner join formationphp.evenement a on e.cin= a.cin";
		$sql="SElECT * From evenement";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }	
	}
	function supprimerEvenement($titre){
		$sql="DELETE FROM evenement where id= :titre";
		$db = config::getConnexion();
        $req=$db->prepare($sql);
		$req->bindValue(':titre',$titre);
		try{
            $req->execute();
           // header('Location: index.php');
        }
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
	function modifierEvenement($evenement,$cin){
		$sql="UPDATE evenement SET titre=:titre, type=:type, date_fin=:date_fin,description=:description,lien=:lien, places=:places WHERE id=:cin";
		
		$db = config::getConnexion();
		//$db->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
try{		
        $req=$db->prepare($sql);
        $titre=$evenement->gettitre();
        $description=$evenement->getdescription();
        $date_fin=$evenement->getdate_fin();
        $type=$evenement->gettype();
        $places=$evenement->getplaces();
        $lien=$evenement->getlien();        
		$datas = array(':cin'=>$cin, ':titre'=>$titre, ':type'=>$type, ':lien'=>$lien,':date_fin'=>$date_fin,':description'=>$description, ':places'=>$places);
		$req->bindValue(':cin',$cin);
		$req->bindValue(':lien',$lien);
		$req->bindValue(':description',$description);
		$req->bindValue(':date_fin',$date_fin);
		$req->bindValue(':type',$type);
		$req->bindValue(':titre',$titre);
		$req->bindValue(':places',$places);
		
		
            $s=$req->execute();
			
           // header('Location: index.php');
        }
        catch (Exception $e){
            echo " Erreur ! ".$e->getMessage();
   echo " Les datas : " ;
  print_r($datas);
        }
		
	}
	function placesEvenement($cin,$places){
		$sql="UPDATE evenement SET places=:places WHERE id=:cin";
		
		$db = config::getConnexion();
		//$db->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
try{		
        $req=$db->prepare($sql);
        
		$datas = array(':cin'=>$cin, ':places'=>$places);
		$req->bindValue(':cin',$cin);
		$req->bindValue(':places',$places);
		
		
            $s=$req->execute();
			
           // header('Location: index.php');
        }
        catch (Exception $e){
            echo " Erreur ! ".$e->getMessage();
   echo " Les datas : " ;
  print_r($datas);
        }
		
	}
	function recupererEvenement($cin){
		$sql="SELECT * from evenement where id=$cin";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
	
	function rechercherListeEvenements($tarif){
		$sql="SELECT * from evenement where tarifHoraire=$tarif";
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