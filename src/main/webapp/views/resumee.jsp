<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Resume</title>
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.0/css/all.min.css'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/rstyle.css">

</head>
<body>
<!-- partial:index.partial.html -->
<!-- google fonts -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i,900,900i" rel="stylesheet">


<br>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<td class="border-bottom-0"><a href="#" class="btn btn-primary">Back</a></td>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<td class="border-bottom-0"><a href="#" class="btn btn-primary">Download</a></td>

<!-- cv -->
<div class="cv">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-8">

        <!-- header -->
        <header class="cv-header">
          <img class="cv-header__img" src="data:image/jpg;base64,${pfile} alt="Girl in a jacket" data-zoomable>
          <div class="cv-header__text-container">
            <h1 class="cv-header__name">${cname }</h1>
          </div>
        </header>

        <!-- content -->
        <section class="cv-section">
          <h2 class="cv-section__title"><i class="fas fa-user fa-fw"></i>Skills</h2>
          <div class="cv-section__wrapper">
            <p class="cv-section__description">${mskills}<br><br></p>
             
          </div>
        </section>

        <!-- experience -->
        <section class="cv-section">
          <h2 class="cv-section__title"><i class="fas fa-briefcase fa-fw"></i>Projects</h2>
          <div class="cv-section__wrapper">
            
            <!-- freelance -->
            <div class="cv-section__experience">

              <p class="cv-section__description">${nproject}</p>
            </div>
          </div>
        </section>
        
        <!-- education -->
        <section class="cv-section">
          <h2 class="cv-section__title"><i class="fas fa-graduation-cap fa-fw"></i> Education</h2>
          <div class="cv-section__wrapper">
            
            <!-- ravensbourne -->
            <div class="cv-section__experience">
              <p class="cv-section__role">Ravensbourne University London – Web Media BA</p>
              <p class="cv-section__period">2012 – 2015</p>
              <p class="cv-section__description">First-class honours degree.</p>
            </div>
            
            <!-- undertone -->
            <div class="cv-section__experience">
              <p class="cv-section__role">Tower Hamlets College – Art & Design (Interactive Media) BTEC & Media Studies</p>
              <p class="cv-section__period">2010 – 2012</p>
              <p class="cv-section__description">Distinction, Merit, Merit & C.</p>
            </div>
            
            <!-- doner -->
            <div class="cv-section__experience">
              <p class="cv-section__role">Langdon Park School – Graphics, Geography & History</p>
              <p class="cv-section__period">2005 – 2010</p>
              <p class="cv-section__description">13 GCSEs at grades A*-C including English, Maths and Science.</p>
            </div>

          </div>
        </section>

      </div>

      <!-- sidebar -->
      <aside class="col-md-4 cv-sidebar">

        <!-- details -->
        <section class="cv-sidebar-section">
          <h2 class="cv-sidebar-section__title">Details</h2>
          <p class="cv-sidebar-section__description">
            East London, UK <br>
            <a href="tel:0-123-456-7890" rel="nofollow">012 345 67890</a> <br>
            <a href="mailto:example@selcukcura.com" rel="nofollow">example@selcukcura.com</a> <br>
            <a href="https://selcukcura.com" target="_blank">selcukcura.com</a>
          </p>
        </section>

        <!-- skills -->
        <section class="cv-sidebar-section">
          <h2 class="cv-sidebar-section__title">Skills</h2>
          <p class="cv-sidebar-section__description">HTML, CSS, JavaScript, jQuery, Greensock, Bootstrap, SCSS, LESS, WordPress, Handlebars, JSON, AJAX, Git, Gulp, Grunt, SEO.</p>
        </section>

        <!-- tools -->
        <section class="cv-sidebar-section">
          <h2 class="cv-sidebar-section__title">Tools</h2>
          <p class="cv-sidebar-section__description">MacOS, Windows, Atom, Sketch, Photoshop, Illustrator, MAMP, Sourcetree, Bitbucket, Google Analytics.</p>
        </section>

        <!-- interests -->
        <section class="cv-sidebar-section">
          <h2 class="cv-sidebar-section__title">Interests</h2>
          <p class="cv-sidebar-section__description">Minimalism, Animation, Concept Development, Responsive Design, UI Design, UX Design, Interaction Design, Photography.</p>
        </section>

        <!-- languages -->
        <section class="cv-sidebar-section">
          <h2 class="cv-sidebar-section__title">Languages</h2>
          <p class="cv-sidebar-section__description">English, Turkish.</p>
        </section>

      </aside>
    </div>
  </div>
<!-- partial -->
  <script src='https://cdn.jsdelivr.net/npm/medium-zoom@1.0.3/dist/medium-zoom.min.js'></script><

</body>
</html>
