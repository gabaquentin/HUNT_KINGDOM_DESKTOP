<?PHP
class partenairesC {
	function ajouterPartenaires($partenaires,$proprietaire){
		$sql="insert into partenaire (photo,nom,description,classe,email,proprietaire) values (:photo, :nom,:description,:classe,:email,:proprietaire)";
		$db = config::getConnexion();
		try{
        $req=$db->prepare($sql);
		
        $photo=$partenaires->getphoto();
        $nom=$partenaires->getNom();
        $description=$partenaires->getdescription();
        $classe=$partenaires->getclasse();        
        $email=$partenaires->getemail();
		$req->bindValue(':photo',$photo);
		$req->bindValue(':nom',$nom);
		$req->bindValue(':description',$description);
		$req->bindValue(':classe',$classe);	
		$req->bindValue(':email',$email);		
		$req->bindValue(':proprietaire',$proprietaire);		
            $req->execute();
           
        }
        catch (Exception $e){
            echo 'Erreur: '.$e->getMessage();
        }
		
	}

	function ajouterRecherche($code, $libelle)
	{
		$sql = "insert into recherche (code,libelle) values (:code, :libelle)";
		$db = config::getConnexion();
		try {
			$req = $db->prepare($sql);

			$req->bindValue(':code', $code);
			$req->bindValue(':libelle', $libelle);
			$req->execute();
		} catch (Exception $e) {
			echo 'Erreur: ' . $e->getMessage();
		}
	}
	
	function afficherPartenaires(){
		//$sql="SElECT * From partenaires e inner join formationphp.partenaires a on e.cin= a.cin";
		$sql="SElECT * From partenaire ORDER BY classe";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }	
	}
	
	function afficherPartenaires6(){
		//$sql="SElECT * From partenaires e inner join formationphp.partenaires a on e.cin= a.cin";
		$sql="SElECT * From partenaire ORDER BY date_ajout DESC LIMIT 6";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }	
	}

	function supprimerPartenaires($photo){
		$sql="DELETE FROM partenaire where id= :photo";
		$db = config::getConnexion();
        $req=$db->prepare($sql);
		$req->bindValue(':photo',$photo);
		try{
            $req->execute();
           // header('Location: index.php');
        }
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
	
	function modifierValidite($validite,$cin){
		$sql="UPDATE partenaire SET statut=:validite WHERE id=:cin";
		
		$db = config::getConnexion();
		//$db->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
try{		
        $req=$db->prepare($sql);

		$datas = array(':cin'=>$cin,':validite'=>$validite);
		$req->bindValue(':cin',$cin);
		$req->bindValue(':validite',$validite);
		
		
            $s=$req->execute();
			
           // header('Location: index.php');
        }
        catch (Exception $e){
            echo " Erreur ! ".$e->getMessage();
   echo " Les datas : " ;
  print_r($datas);
        }
		
	}
	
	function modifierClasse($classe,$cin){
		$sql="UPDATE partenaire SET classe=:classe WHERE id=:cin";
		
		$db = config::getConnexion();
		//$db->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
try{		
        $req=$db->prepare($sql);

		$req->bindValue(':cin',$cin);
		$req->bindValue(':classe',$classe);
		
		
            $s=$req->execute();
			
           // header('Location: index.php');
        }
        catch (Exception $e){
            echo " Erreur ! ".$e->getMessage();
        }
		
	}
	
	function modifierPartenaire($partenaires,$proprietaire,$cin){
		$sql="UPDATE partenaire SET email=:email,description=:description,nom=:nom,proprietaire=:proprietaire WHERE id=:cin";
		
		$db = config::getConnexion();
		//$db->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
try{		
        $req=$db->prepare($sql);
        $nom=$partenaires->getNom();
        $description=$partenaires->getdescription();
        $email=$partenaires->getemail();
		$datas = array(':cin'=>$cin, ':email'=>$email, ':nom'=>$nom,':proprietaire'=>$proprietaire,':description'=>$description);
		$req->bindValue(':cin',$cin);
		$req->bindValue(':nom',$nom);
		$req->bindValue(':description',$description);
		$req->bindValue(':proprietaire',$proprietaire);
		$req->bindValue(':email',$email);
		
		
            $s=$req->execute();
			
           // header('Location: index.php');
        }
        catch (Exception $e){
            echo " Erreur ! ".$e->getMessage();
   echo " Les datas : " ;
  print_r($datas);
        }
		
	}
	
	function modifierPartenaires($partenaires,$proprietaire,$cin){
		$sql="UPDATE partenaire SET photo=:photo,email=:email,description=:description,nom=:nom,proprietaire=:proprietaire WHERE id=:cin";
		
		$db = config::getConnexion();
		//$db->setAttribute(PDO::ATTR_EMULATE_PREPARES,false);
try{		
        $req=$db->prepare($sql);
        $nom=$partenaires->getNom();
        $photo=$partenaires->getPhoto();
        $description=$partenaires->getdescription();
        $email=$partenaires->getemail();
		$datas = array(':cin'=>$cin, ':email'=>$email, ':nom'=>$nom,':proprietaire'=>$proprietaire,':description'=>$description);
		$req->bindValue(':cin',$cin);
		$req->bindValue(':photo',$photo);
		$req->bindValue(':nom',$nom);
		$req->bindValue(':description',$description);
		$req->bindValue(':proprietaire',$proprietaire);
		$req->bindValue(':email',$email);
		
		
            $s=$req->execute();
			
           // header('Location: index.php');
        }
        catch (Exception $e){
            echo " Erreur ! ".$e->getMessage();
   echo " Les datas : " ;
  print_r($datas);
        }
		
	}
	
	function recupererPartenaires($cin){
		$sql="SELECT * from partenaire where id=$cin";
		$db = config::getConnexion();
		try{
		$liste=$db->query($sql);
		return $liste;
		}
        catch (Exception $e){
            die('Erreur: '.$e->getMessage());
        }
	}
	
	function rechercherListePartenairess($tarif){
		$sql="SELECT * from partenaire where tarifHoraire=$tarif";
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