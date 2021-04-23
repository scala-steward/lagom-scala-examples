scalacOptions --=
  "-Wunused:implicits" ::
    "-Wunused:imports" ::
    "-Wunused:locals" ::
    "-Wunused:params" ::
    "-Wunused:privates" ::
    Nil

scalacOptions ++=
  "-Ymacro-annotations" ::
    "-Ytasty-reader" ::
    Nil
