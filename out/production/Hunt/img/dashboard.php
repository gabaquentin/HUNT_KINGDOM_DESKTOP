<?php
session_start();

if (!isset($_SESSION["pseudo2"])) {
  header("location: index.php");
}

include 'codex/statistiques.php';

?>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Start your development with a Dashboard for Bootstrap 4.">
  <meta name="author" content="Creative Tim">
  <title>Accueil</title>
  <!-- Favicon -->
  <link href="./assets/img/brand/favicon.png" rel="icon" type="image/png">
  <!-- Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <!-- Icons -->
  <link href="./assets/vendor/nucleo/css/nucleo.css" rel="stylesheet">
  <link href="./assets/vendor/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet">
  <!-- Argon CSS -->
  <link type="text/css" href="./assets/css/argon.css?v=1.0.0" rel="stylesheet">
</head>

<body>
  <!-- Sidenav -->
  <?php include "codex/navigation.php"; ?>
  <!-- Main content -->
  <?php include "codex/header.php"; ?>
  <!-- Page content -->
  <div class="container-fluid mt--7">
    <div class="row">
      <div class="col-xl-8 mb-5 mb-xl-0">
        <div class="card bg-gradient-default shadow">
          <div class="card-header bg-transparent">
            <div class="row align-items-center">
              <div class="col">
                <h6 class="text-uppercase text-light ls-1 mb-1">Overview</h6>
                <h2 class="text-white mb-0">Ventes et Visites <?php echo $rech; ?></h2>
              </div>
              <div class="col">
                <ul class="nav nav-pills justify-content-end">
                  <li class="nav-item mr-2 mr-md-0" data-toggle="chart" data-target="#chart-sales" data-update='{"data":{"datasets":[{"data":[<?php echo $janvier; ?>, <?php echo $fevrier; ?>, <?php echo $mars; ?>, <?php echo $avril; ?>, <?php echo $mai; ?>, <?php echo $juin; ?>, <?php echo $juillet; ?>, <?php echo $aout; ?>, <?php echo $septembre; ?>, <?php echo $octobre; ?>, <?php echo $novembre; ?>, <?php echo $decembre; ?>]}]}}' data-prefix="" data-suffix=" FCFA">
                    <a href="#" class="nav-link py-2 px-3 active" data-toggle="tab">
                      <span class="d-none d-md-block">Ventes</span>
                      <span class="d-md-none">V</span>
                    </a>
                  </li>
                  <li class="nav-item mr-2 mr-md-0" data-toggle="chart" data-target="#chart-sales" data-update='{"data":{"datasets":[{"data":[<?php echo $janvierV; ?>, <?php echo $fevrierV; ?>, <?php echo $marsV; ?>, <?php echo $avrilV; ?>, <?php echo $maiV; ?>, <?php echo $juinV; ?>, <?php echo $juilletV; ?>, <?php echo $aoutV; ?>, <?php echo $septembreV; ?>, <?php echo $octobreV; ?>, <?php echo $novembreV; ?>, <?php echo $decembreV; ?>]}]}}' data-prefix="" data-suffix="">
                    <a href="#" class="nav-link py-2 px-3 active" data-toggle="tab">
                      <span class="d-none d-md-block">Visites</span>
                      <span class="d-md-none">V</span>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="card-body">
            <!-- Chart -->
            <div class="chart">
              <!-- Chart wrapper -->
              <canvas id="chart-sales" class="chart-canvas"></canvas>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xl-4">
        <div class="card shadow">
          <div class="card-header bg-transparent">
            <div class="row align-items-center">
              <div class="col">
                <h6 class="text-uppercase text-muted ls-1 mb-1">Performance</h6>
                <h2 class="mb-0">Total commandes</h2>
              </div>

              <div class="col">
                <ul class="nav nav-pills justify-content-end">
                  <li class="nav-item mr-2 mr-md-0" data-toggle="chart" data-target="#chart-orders" data-update='{"data":{"datasets":[{"data":[<?php echo $janvierO; ?>, <?php echo $fevrierO; ?>, <?php echo $marsO; ?>, <?php echo $avrilO; ?>, <?php echo $maiO; ?>, <?php echo $juinO; ?>, <?php echo $juilletO; ?>, <?php echo $aoutO; ?>, <?php echo $septembreO; ?>, <?php echo $octobreO; ?>, <?php echo $novembreO; ?>, <?php echo $decembreO; ?>]}]}}' data-prefix="" data-suffix="">
                    <a href="#" class="nav-link py-2 px-3 active" data-toggle="tab">
                      <span class="d-none d-md-block">Actualiser</span>
                      <span class="d-md-none">M</span>
                    </a>
                  </li>
                </ul>
              </div>

            </div>
          </div>
          <div class="card-body">
            <!-- Chart -->
            <div class="chart">
              <canvas id="chart-orders" class="chart-canvas"></canvas>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mt-5">

      <div class="col-xl-8 mb-5 mb-xl-0" id="mpr">
        <div class="card shadow">
          <div class="card-header border-0">
            <div class="row align-items-center">
              <div class="col">
                <h3 class="mb-0">Marques les plus recherchées </h3>
              </div>
              <div class="col text-right">
                <a href="dashboard.php?i=2#mpr" class="btn btn-sm btn-primary">Voir tout</a>
              </div>
            </div>
          </div>
          <div class="table-responsive">
            <!-- Projects table -->
            <table class="table align-items-center table-flush">
              <thead class="thead-light">
                <tr>
                  <th scope="col">Referral</th>
                  <th scope="col">Occurence</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>

                <?php
                $nFR = count($finalR);
                $i = 0;
                foreach ($finalR as $key => $val) {
                  $percent = ($val / $totalCR) * 100;
                ?>
                  <tr>
                    <th scope="row">
                      <?php echo $key; ?>
                    </th>
                    <td>
                      <?php echo $val; ?>
                    </td>
                    <td>
                      <div class="d-flex align-items-center">
                        <span class="mr-2"><?php echo intval($percent); ?>%</span>
                        <div>
                          <div class="progress">
                            <div class="progress-bar <?php if(intval($percent) > 70){echo "bg-gradient-primary";}else if(intval($percent) > 30){echo "bg-gradient-success";}else{echo "bg-gradient-danger";} ?>" role="progressbar" aria-valuenow="<?php echo $percent; ?>" aria-valuemin="0" aria-valuemax="100" style="width: <?php echo intval($percent); ?>%;"></div>
                          </div>
                        </div>
                      </div>
                    </td>
                  </tr>
                <?php
                  if (($i == 1) and (!isset($_GET['i']))) {
                    break;
                  }

                  $i += 1;
                }
                ?>

              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
      <div class="row align-items-center justify-content-xl-between">
        <div class="col-xl-6">
          <div class="copyright text-center text-xl-left text-muted">
            &copy; 2018 <a href="https://www.creative-tim.com" class="font-weight-bold ml-1" target="_blank">Creative Tim</a>
          </div>
        </div>
        <div class="col-xl-6">
          <ul class="nav nav-footer justify-content-center justify-content-xl-end">
            <li class="nav-item">
              <a href="https://www.creative-tim.com" class="nav-link" target="_blank">Creative Tim</a>
            </li>
            <li class="nav-item">
              <a href="https://www.creative-tim.com/presentation" class="nav-link" target="_blank">About Us</a>
            </li>
            <li class="nav-item">
              <a href="http://blog.creative-tim.com" class="nav-link" target="_blank">Blog</a>
            </li>
            <li class="nav-item">
              <a href="https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md" class="nav-link" target="_blank">MIT License</a>
            </li>
          </ul>
        </div>
      </div>
    </footer>
  </div>
  </div>
  <!-- Argon Scripts -->
  <!-- Core -->
  <script src="./assets/vendor/jquery/dist/jquery.min.js"></script>
  <script src="./assets/vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Optional JS -->
  <script src="./assets/vendor/chart.js/dist/Chart.min.js"></script>
  <script src="./assets/vendor/chart.js/dist/Chart.extension.js"></script>
  <!-- Argon JS -->
  <script src="./assets/js/argon.js?v=1.0.0"></script>
</body>

</html>