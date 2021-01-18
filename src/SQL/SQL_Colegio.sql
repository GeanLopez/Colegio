use colegio;
insert into Alumno (nombre, apellido,correo,id_tipo,contraseña) 
values ("Mario","Guzman","Guzman@gmail.com",1,"123456");

use colegio;
select * from Alumno;

/*Verificación de Usuario*/
use colegio;
select contraseña from Alumno where correo="josue@gmail.com";

/*Verificación de Usuario tipo*/
use colegio;
select contraseña, id_tipo from Alumno where correo="josue@gmail.com";

use colegio;
insert into Docente (Id_Especialidad,nombre, apellido,correo,id_tipo,contraseña) 
values (2,"Alberto","Moreno Cueva","Cueva@gmail.com",2,"123456");
/*Editar Alumno*/
use colegio;
update Alumno set nombre="Juan", apellido="Cueva",correo="Penes@gmail.com",telefono=981548465,dni=1257456,sexo="Cabro",Fecha_Nacimiento="2000-05-25" where id_alumno=3;
/*Editar Docente*/
use colegio;
update Docente set nombre="Ivan", apellido="Martinez Putin",correo="Penes@gmail.com",telefono=981548465,dni=5465465,sexo="Idefinido",Fecha_Nacimiento="2000-05-25" where id_Docente=3;
/*Cambiar Contraseña Alumno*/
use colegio;
update Alumno set contraseña="penes" where id_Alumno=3;

/*Cambiar Contraseña Docente*/
use colegio;
update Docente set contraseña="gampi" where id_Docente=3;

use colegio;
update Docente set avatar="gampi" where id_Docente=3;

use colegio;
select Id_Notas,pc1,pc2,ep,pc3,ef from notas where id_curso=1 and id_alumno=2;
/*select Id_Notas,pc1,pc2,ep,pc3,ef from notas;*/

/*Editar Notas*/
use colegio;
update Notas set pc1=16,pc2=15,ep=14,pc3=20,ef=20 where id_notas=1;

/*Agregar Nota*/
use colegio;
insert into Notas (pc1,pc2,ep,pc3,ef,id_alumno,id_curso)
values (20,18,20,17,15,2,1);

use colegio;
insert into incripciones (id_curso,id_alumno)
values (1,1);


use colegio;
select c.id_curso, nombre_curso, descripcion, id_docente from Cursos c, incripciones i where c.id_curso=i.id_curso and id_alumno=1;

/*Mensaje Docente*/
use colegio;
select Id_Mensaje, Mensaje from Mensajeria where id_docente=1;

/*Mensaje Alumno*/
use colegio;
select Id_Mensaje, Mensaje from Mensajeria where id_alumno=1;


use colegio;
insert into mensajeria (mensaje, id_alumno,id_docente)
values ("Hola Puto",1,1);

use colegio;
select c.id_curso, nombre_curso, descripcion,id_docente from Cursos c, incripciones i where c.id_curso=i.id_curso and id_docente=1;

/*ambiguo se repite las tablas*/
use colegio;
select a.id_alumno, nombre, apellido from Cursos c, incripciones i, alumno a where c.id_curso=i.id_curso and a.id_alumno=i.id_alumno and i.id_curso=1;

use colegio;
select id_curso from Cursos  where id_docente=1 ;