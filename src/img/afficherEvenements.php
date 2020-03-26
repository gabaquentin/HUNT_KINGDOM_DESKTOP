<?PHP
include_once "config.php";
include_once "entities/evenement.php";
include_once "core/evenementC.php";
include_once "entities/participant.php";
include_once "core/participantC.php";
$evenement1C=new evenementC();
$listeevenements=$evenement1C->afficherEvenement();

  function participe($perso,$event)
  {
    $participant1=new participantC();
    $participe=$participant1->afficherparticipant();

    $verif=0;
    foreach ($participe as $raw) {
      if ($raw["id_participant"]==$perso && $raw["id_evenement"]==$event) {
        $verif=1;
      }
    }
    return $verif;
  }
//var_dump($listeevenements->fetchAll());
foreach($listeevenements as $row){
  if($row['type']!=3){
  ?>


<div class="item-slick1" style="background-image: url(apercu4.php?id_img=<?PHP echo $row['id']; ?>);">
          <div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
            <h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
              <?PHP echo $row['titre']; ?>
            </h2>

            <span class="caption1-slide1 m-text1 t-center animated visible-false m-b-15" data-appear="fadeInDown">
              <?PHP echo $row['description']; ?><br> <?php if($row['type']==1){ ?>il reste <?PHP echo $row['places']; ?> places jusqu'au <?PHP echo $row['date_fin']; }?>
            </span>

            <div class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
              <!-- Button -->
              
                <?php if($row['type']==1 and isset($_SESSION['pseudo1']))
                {
                    if (participe($_SESSION['id1'],$row["id"])==0) {
                      if ($row['places']!=0) {
                       
                      
                  ?>
                    <a href="views/ajoutParticipant.php?event=<?php echo $row["id"]; ?>&lien=<?php echo $row["lien"]; ?>&places=<?php echo $row["places"]; ?>" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">Participer</a>
                      <?php
                      }
                      else{
                        ?>
                    <a href="#" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">Complet</a>
                      <?php
                      }
                    }
                    else {
                      ?>
                    <a href="" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">Participe déjà</a>
                      <?php
                    }
                } 
                ?>
                
              
            </div>

          </div>
        </div>

	
<?php	
}
}
?>



<?php

?>

