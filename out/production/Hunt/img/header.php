  <div class="main-content">
    <!-- Top navbar -->
    <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
      <div class="container-fluid">
        <!-- Brand -->
        <!-- Form -->
        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
          <div class="form-group mb-0">
            <div class="input-group input-group-alternative">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="fas fa-search"></i></span>
              </div>
              <input class="form-control" placeholder="Search" type="text">
            </div>
          </div>
        </form>
        <!-- User -->
        <ul class="navbar-nav align-items-center d-none d-md-flex">
          <li class="nav-item dropdown">
            <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <div class="media align-items-center">
                <span class="avatar avatar-sm rounded-circle">
                  <?php
                  if (isset($_SESSION["pseudo2"])) {
                    if ($_SESSION["fonction1"] == "administrateur") {
                      ?>
                      <img alt="Image placeholder" src="./assets/img/theme/sketch.jpg">
                    <?php
                      } else if ($_SESSION["fonction1"] == "fournisseur") {
                        ?>
                      <img alt="Image placeholder" src="./assets/img/theme/vue.jpg">
                  <?php
                    }
                  }
                  ?>
                </span>
                <div class="media-body ml-2 d-none d-lg-block">
                  <span class="mb-0 text-sm  font-weight-bold">
                    <?php
                    if (isset($_SESSION["pseudo2"])) {
                      echo $_SESSION["pseudo2"];
                    }
                    ?>
                  </span>
                </div>
              </div>
            </a>
            <?php include "menuProfil.php"; ?>
          </li>
        </ul>
      </div>
    </nav>
    <!-- Header -->
    <div class="header bg-gradient-primary pb-8 pt-5 pt-md-8">
      <div class="container-fluid">
        <div class="header-body">
          <!-- Card stats -->
          <div class="row">
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Abonnes</h5>
                      <span class="h2 font-weight-bold mb-0"><?php echo $nbabonnes; ?></span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-gray text-black rounded-circle shadow">
                        <i class="fas fa-users"></i>
                      </div>
                    </div>
                  </div>
                  <p class="mt-3 mb-0 text-muted text-sm">
                    <span class="text-success mr-2"><i class="fa fa-arrow-up"></i> 0%</span>
                    <span class="text-nowrap">depuis le dernier mois</span>
                  </p>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Fournisseurs</h5>
                      <span class="h2 font-weight-bold mb-0"><?php echo $nbfournisseurs; ?></span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-blue text-white rounded-circle shadow">
                        <i class="fas fa-chart-bar"></i>
                      </div>
                    </div>
                  </div>
                  <p class="mt-3 mb-0 text-muted text-sm">
                    <span class="text-danger mr-2"><i class="fas fa-arrow-down"></i> 0%</span>
                    <span class="text-nowrap">depuis le dernier mois</span>
                  </p>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Produits</h5>
                      <span class="h2 font-weight-bold mb-0"><?php echo $nbproduits; ?></span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-yellow text-white rounded-circle shadow">
                        <i class="fas fa-chart-bar"></i>
                      </div>
                    </div>
                  </div>
                  <p class="mt-3 mb-0 text-muted text-sm">
                    <span class="text-warning mr-2"><i class="fas fa-arrow-down"></i> 0%</span>
                    <span class="text-nowrap">depuis le dernier mois</span>
                  </p>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Maisons</h5>
                      <span class="h2 font-weight-bold mb-0"><?php echo $nbpartenaires; ?></span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-info text-white rounded-circle shadow">
                        <i class="fas fa-book"></i>
                      </div>
                    </div>
                  </div>
                  <p class="mt-3 mb-0 text-muted text-sm">
                    <span class="text-success mr-2"><i class="fas fa-arrow-up"></i> 0%</span>
                    <span class="text-nowrap">depuis le dernier mois</span>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>