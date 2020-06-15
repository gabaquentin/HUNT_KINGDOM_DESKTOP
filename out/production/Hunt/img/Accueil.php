<?php
session_start();
include_once "config.php";
include_once "entities/panier.php";
include_once "core/panierC.php";

?>
<!DOCTYPE html>
<html lang="fr">

<head>
	<title>Accueil</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.png" />
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/themify/themify-icons.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/elegant-font/html-css/style.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<!--===============================================================================================-->
</head>

<body class="animsition">

	<!-- Header -->
	<header class="header1">
		<?php include "codex/header.php"; ?>
	</header>

	<!-- Slide1 -->
	<section class="slide1">
		<div class="wrap-slick1">
			<div class="slick1">

				<?php

				$id = "";
				$nom = "";
				$prix = "";
				$description = "";
				$prixpromo = "";
				$categorie = "";
				$fournisseur = "";
				$quantite = "";
				$type = "";
				$validite = "";
				$creation = "";



				include_once "entities/produits.php";
				include_once "core/produitsC.php";


				//include "entities/partenaires.php";
				include "core/partenairesC.php";
				include "core/adminC.php";


				$produitsC = new produitsC();
				$listeProduits = $produitsC->afficherProduits();

				foreach ($listeProduits as $row) {

					if ($row['validite'] == 1) {
						$id = $row['id'];
						$nom = strtoupper($row['nom']);
						$prix = $row['prix'];
						$prixpromo = $row['prix_promo'];
						$description = $row['description'];
						$categorie = $row['categorie'];
						$souscategorie = $row['souscategorie'];
						$fournisseur = $row['boutique'];
						$quantite = $row['quantite'];
						$type = $row['type'];
						$validite = $row['validite'];
						$creation = $row['ajout'];
						$dis = time() - strtotime($creation); //difference_in_seconds
						if ($dis <= 350000) {
							?>

							<div class="item-slick1 item1-slick1" style="background-image: url(views/apercu.php?id_img=<?php echo $id; ?>);">
								<div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
									<h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
										<?php echo $nom; ?>
									</h2>
									<span class="caption1-slide1 m-text1 t-center animated visible-false m-b-15" data-appear="fadeInDown">
										<?php echo $souscategorie ?> Collection de la categorie <?php echo $categorie ?>
									</span>

									<h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
										NOUVEAU
									</h2>

									<div class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
										<!-- Button -->
										<?php if ($type == "service") { ?>
											<a href="service-detail.php?id=<?php echo $id; ?>" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
												voir
											</a>
										<?php } else if ($type == "produit") { ?>
											<a href="product-detail.php?id=<?php echo $id; ?>" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
												voir
											</a>
										<?php } ?>
									</div>
								</div>
							</div>

						<?php
								}
								if ($prixpromo != 0) {
									?>
							<div class="item-slick1 item1-slick1" style="background-image: url(views/apercu.php?id_img=<?php echo $id; ?>);">
								<div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
									<h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
										<?php echo $nom; ?>
									</h2>
									<span class="caption1-slide1 m-text1 t-center animated visible-false m-b-15" data-appear="fadeInDown">
										<?php echo $souscategorie ?> Collection de la categorie <?php echo $categorie ?>
									</span>

									<h2 class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
										EN SOLDE
									</h2>

									<div class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
										<!-- Button -->
										<a href="product-detail.php?id=<?php echo $id; ?>" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
											voir
										</a>
									</div>
								</div>
							</div>
				<?php
						}
					}
				}

				?>

			</div>
		</div>
	</section>

	<!-- Banner -->
	<section class="banner bgwhite p-t-40 p-b-40">
		<div class="container">
			<div class="row">

				<?php

				if (!isset($_SESSION['pseudo1'])) {

					?>

					<div class="col-sm-10 col-md-8 col-lg-4 m-l-r-auto">
						<!-- block1 -->

						<!-- block2 -->
						<div class="block2 wrap-pic-w pos-relative m-b-30">
							<img src="images/icons/bg-01.jpg" alt="IMG">

							<div class="block2-content sizefull ab-t-l flex-col-c-m">
								<h4 class="m-text4 t-center w-size3 p-b-8">
									Inscrivez vous & obtenez des points de fidelités gratuits
								</h4>

								<p class="t-center w-size4">
									Be the frist to know about the latest fashion news and get exclu-sive offers made in cameroon ;)
								</p>

								<div class="w-size2 p-t-25">
									<!-- Button -->
									<a href="connexion/inscription.php" class="flex-c-m size2 bg4 bo-rad-23 hov1 m-text3 trans-0-4">
										Inscription
									</a>
								</div>
							</div>
						</div>
					</div>
				<?php

				}

				?>
			</div>
		</div>
	</section>

	<!-- New Product -->
	<section class="newproduct bgwhite p-t-45 p-b-105">
		<div class="container">
			<div class="sec-title p-b-60">
				<h3 class="m-text5 t-center">
					Recommandé pour vous
				</h3>
			</div>
			<!-- Slide2 -->
			<div class="wrap-slick2">
				<button class="arrow-slick2 prev-slick2 slick-arrow" style=""><i class="fa  fa-angle-left" aria-hidden="true"></i></button>
				<div class="slick2">

					<?php

					$id2 = "";
					$nom2 = "";
					$prix2 = "";
					$description2 = "";
					$prixpromo2 = "";
					$categorie2 = "";
					$fournisseur2 = "";
					$quantite2 = "";
					$type2 = "";
					$validite2 = "";
					$creation2 = "";


					$produitsC = new produitsC();
					$listeProduits = $produitsC->afficherProduitsRand();

					foreach ($listeProduits as $row) {

						if ($row['validite'] == 1) {
							$id2 = $row['id'];
							$nom2 = strtoupper($row['nom']);
							$prix2 = $row['prix'];
							$prixpromo2 = $row['prix_promo'];
							$description2 = $row['description'];
							$categorie2 = $row['categorie'];
							$souscategorie2 = $row['souscategorie'];
							$fournisseur2 = $row['boutique'];
							$quantite2 = $row['quantite'];
							$type2 = $row['type'];
							$validite2 = $row['validite'];
							$creation2 = $row['ajout'];
							$dis2 = time() - strtotime($creation); //difference_in_seconds
							?>
							<div class="item-slick2 p-l-15 p-r-15">
								<!-- Block2 -->
								<div class="block2">
									<div class="block2-img wrap-pic-w of-hidden pos-relative" style="border-radius:10px; height:300px;width:300px; padding:10px;">
										<img src="views/apercu.php?id_img=<?php echo $id2; ?>" alt="IMG-PRODUCT">

										<div class="block2-overlay trans-0-4">
											<a href="#" class="block2-btn-addwishlist hov-pointer trans-0-4">
												<i class="icon-wishlist icon_heart_alt" aria-hidden="true"></i>
												<i class="icon-wishlist icon_heart dis-none" aria-hidden="true"></i>
											</a>

											<div class="block2-btn-addcart w-size1 trans-0-4">
												<!-- Button -->
												<a href="product-detail.php?id=<?php echo $id2; ?>" class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
													voir
												</a>
											</div>
										</div>
									</div>
									<div class="block2-txt p-t-20">
										<?php if ($type2 == "produit") {
													?>
											<a href="product-detail.php?id=<?php echo $id2; ?>" class="block2-name dis-block s-text3 p-b-5">
												<?php echo $nom2; ?>
											</a>
										<?php
												} else if ($type2 == "service") {
													?>

											<a href="service-detail.php?id=<?php echo $id2; ?>" class="block2-name dis-block s-text3 p-b-5">
												<?php echo $nom2; ?>
											</a>

										<?php
												}
												if ($prixpromo2 > 0 && $prixpromo2 < $prix2) {
													?>
											<span class="block2-oldprice m-text7 p-r-5">
												<?php echo $prix2;  ?> FCFA
											</span>

											<span class="block2-newprice m-text8 p-r-5">
												<?php echo $prixpromo2;  ?> FCFA
											</span>
										<?php
												} else {
													?>
											<span class="block2-price m-text6 p-r-5">
												<?php echo $prix2;  ?> FCFA
											</span>
										<?php
												}
												?>
									</div>
								</div>
							</div>

					<?php
						}
					}

					?>
					<button class="arrow-slick2 next-slick2 slick-arrow" style=""><i class="fa  fa-angle-right" aria-hidden="true"></i></button>
				</div>
			</div>

		</div>
	</section>

	<!-- Maison -->

	<section class="blog bgwhite p-t-94 p-b-65">
		<div class="container">
			<div class="sec-title p-b-52">
				<h3 class="m-text5 t-center">
					<a href="house.php" class="block3-img dis-block hov-img-zoom m-text5 t-center">
						Nos Maisons
					</a>
				</h3>
			</div>

			<div class="row">

				<?php

				$id1 = "";
				$nom1 = "";
				$description1 = "";
				$classe1 = "";
				$email1 = "";
				$validite1 = "";
				$proprietaire1 = "";


				$partenairesC = new partenairesC();
				$listePartenaires = $partenairesC->afficherPartenaires6();

				foreach ($listePartenaires as $row) {

					$id1 = $row['id'];
					$nom1 = strtoupper($row['nom']);
					$description1 = $row['description'];
					$classe1 = $row['classe'];
					$email1 = $row['email'];
					$validite1 = $row['statut'];
					$proprietaire1 = $row['proprietaire'];
					$ajout1 = strtotime($row['date_ajout']);
					$date = date('d-M-Y \a\ G:i', $ajout1);


					?>
					<div class="col-sm-10 col-md-4 p-b-30 m-l-r-auto">
						<!-- Block3 -->
						<div class="block3">
							<a href="house-detail.php?id=<?php echo $id1; ?>" class="block3-img dis-block hov-img-zoom">
								<img style="border-radius:10px; height:300px;width:300px; padding:10px;" src="views/apercuPartenaires.php?id_img=<?php echo $id1; ?>" alt="IMG-HOUSE">
							</a>

							<div class="block3-txt p-t-14">
								<h4 class="p-b-7">
									<a href="house-detail.php?id=<?php echo $id1; ?>" class="m-text11">
										<?php echo $nom1; ?>
									</a>
								</h4>

								<span class="s-text6">Par</span> <span class="s-text7"><?php echo $proprietaire1; ?></span>
								<span class="s-text6">Depuis</span> <span class="s-text7"><?php echo $date; ?></span>

								<p class="s-text8 p-t-16">
									<?php echo $description1; ?>
								</p>
							</div>
						</div>
					</div>

				<?php

				}
				?>

			</div>
		</div>
	</section>

	<!-- Blog -->
	<section class="blog bgwhite p-t-94 p-b-65">
		<div class="container">
			<div class="sec-title p-b-52">
				<h3 class="m-text5 t-center">
					NOS BLOGS
				</h3>
			</div>

			<div class="row">
				<?php


				include_once "entities/Blog.php";
				include_once "core/BlogC.php";


				$BlogC = new BlogC();
				$listeBlogs = $BlogC->afficherBlogs();

				foreach ($listeBlogs as $row) {

					$ajout2 = strtotime($row['dates']);
					$date2 = date('d-M-Y \a\ G:i', $ajout2);


					?>
					<div class="col-sm-10 col-md-4 p-b-30 m-l-r-auto">
						<!-- Block3 -->
						<div class="block3">
							<a href="blog-detail.php?id=<?php echo $row['NumBlog']; ?>&amp;nom=<?php echo  $row['nom']; ?>" class="block3-img dis-block hov-img-zoom">
								<img style="border-radius:10px; height:300px;width:300px; padding:10px;" src="views/apercuBlog.php?id_img=<?php echo $row['NumBlog']; ?>" alt="IMG-BLOG">
							</a>

							<div class="block3-txt p-t-14">
								<h4 class="p-b-7">
									<a href="blog-detail.php?id=<?php echo $row['NumBlog']; ?>&amp;nom=<?php echo  $row['nom']; ?>" class="m-text11">
										<?php echo $row['nom']; ?>
									</a>
								</h4>

								<span class="s-text6">Messages : </span> <span class="s-text7"><?php echo $row['messages']; ?></span>
								<span class="s-text6">Depuis</span> <span class="s-text7"><?php echo $date2; ?></span>

								<p class="s-text8 p-t-16">
									<?php echo $row['libelle']; ?>
								</p>
							</div>
						</div>
					</div>

				<?php

				}
				?>
			</div>
		</div>
	</section>

	<!-- Instagram -->
	<section class="instagram p-t-20">
		<div class="sec-title p-b-52 p-l-15 p-r-15">
			<h3 class="m-text5 t-center">
				<a href="https://instagram.com/fameink.0408?igshid=y5pbk04l3fxy" class="m-text5 t-center">
					@ Suivez nous sur instagram
				</a>
			</h3>
		</div>

		<div class="flex-w">
			<!-- LightWidget WIDGET -->
			<!-- LightWidget WIDGET -->
			<!-- LightWidget WIDGET -->
			<!-- LightWidget WIDGET -->
			<script src="https://cdn.lightwidget.com/widgets/lightwidget.js"></script><iframe src="https://cdn.lightwidget.com/widgets/13d3c13cd2845fc2bcc764a2d449eb41.html" scrolling="no" allowtransparency="true" class="lightwidget-widget" style="width:100%;border:0;overflow:hidden;"></iframe>
			<!-- Block4
			<div class="block4 wrap-pic-w">
				<img src="images/gallery-15.jpg" alt="IMG-INSTAGRAM">

				<a href="#" class="block4-overlay sizefull ab-t-l trans-0-4">
					<span class="block4-overlay-heart s-text9 flex-m trans-0-4 p-l-40 p-t-25">
						<i class="icon_heart_alt fs-20 p-r-12" aria-hidden="true"></i>
						<span class="p-t-2">39</span>
					</span>

					<div class="block4-overlay-txt trans-0-4 p-l-40 p-r-25 p-b-30">
						<p class="s-text10 m-b-15 h-size1 of-hidden">
							Nullam scelerisque, lacus sed consequat laoreet, dui enim iaculis leo, eu viverra ex nulla in tellus. Nullam nec ornare tellus, ac fringilla lacus. Ut sit amet enim orci. Nam eget metus elit.
						</p>

						<span class="s-text9">
							Photo by @nancyward
						</span>
					</div>
				</a>
			</div>
			-->
		</div>
	</section>

	<!-- Shipping -->
	<section class="shipping bgwhite p-t-62 p-b-46">
		<div class="flex-w p-l-15 p-r-15">
			<div class="flex-col-c w-size5 p-l-15 p-r-15 p-t-16 p-b-15 respon1">
				<h4 class="m-text12 t-center">
					Livraison sous 24 h dans tout le territoire camerounais
				</h4>
			</div>

			<div class="flex-col-c w-size5 p-l-15 p-r-15 p-t-16 p-b-15 bo2 respon2">
				<h4 class="m-text12 t-center">
					Retour de la commande sous 2 jours
				</h4>
			</div>

		</div>
	</section>


	<!-- Footer -->
	<footer class="bg6 p-t-45 p-b-43 p-l-45 p-r-45">
		<?php include "codex/footer.php"; ?>
	</footer>



	<!-- Back to top -->
	<div class="btn-back-to-top bg0-hov" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
	</div>

	<!-- Container Selection1 -->
	<div id="dropDownSelect1"></div>



	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/bootstrap/js/popper.js"></script>
	<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/select2/select2.min.js"></script>
	<script type="text/javascript">
		$(".selection-1").select2({
			minimumResultsForSearch: 20,
			dropdownParent: $('#dropDownSelect1')
		});
	</script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/slick-custom.js"></script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/lightbox2/js/lightbox.min.js"></script>
	<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
		$('.block2-btn-addcart').each(function() {
			var nameProduct = $(this).parent().parent().parent().find('.block2-name').php();
			$(this).on('click', function() {
				swal(nameProduct, "is added to cart !", "success");
			});
		});

		$('.block2-btn-addwishlist').each(function() {
			var nameProduct = $(this).parent().parent().parent().find('.block2-name').php();
			$(this).on('click', function() {
				swal(nameProduct, "is added to wishlist !", "success");
			});
		});
	</script>

	<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>

</html>