// Script para abrir/fechar colapsáveis
document.addEventListener("DOMContentLoaded", function() {
           var coll = document.getElementsByClassName("collapsible");
           for (var i = 0; i < coll.length; i++) {
               coll[i].addEventListener("click", function() {
                   this.classList.toggle("active");
                   var content = this.nextElementSibling;
                   if (content.style.display === "block") {
                       content.style.display = "none";
                   } else {
                       content.style.display = "block";
                   }
               });
           }

           var subColl = document.getElementsByClassName("sub-collapsible");
           for (var i = 0; i < subColl.length; i++) {
               subColl[i].addEventListener("click", function() {
                   this.classList.toggle("active");
                   var subContent = this.nextElementSibling;
                   if (subContent.style.display === "block") {
                       subContent.style.display = "none";
                   } else {
                       subContent.style.display = "block";
                   }
               });
           }
       });