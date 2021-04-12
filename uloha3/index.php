<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="drtikoule.css" type="text/css">
    </head>
    <body>
        <div class="structure" id="backgroundFP">
            <div class="zatmaveni">
            <h1>Vítejte u představení žampioná: Drtikul</h1>
            <hr>
            <p>Drtikul se může pohybovat po Spojeownu v několika formách, které se liší v pohlaví a barvě pleti.</p>
            <p>Každá forma má své unikátní výhody, ale na ty musí hráči přijít samy.</p>
            <div class="drtikul">
                <img src="images/Drtikul_muzA.png" width="629" height="1000" alt="Drtikul_muzA"/>
                
            </div>
            
         </div>   
        </div>
        
        <div class="structure2 structureSP">
            <div class="secondScroll">
                <div class="wholeContent">
            <h1>DRTIKUL</h1>
            <img src="images/logo.png" width="150" height="150" alt="logo"/>

            <h3>Herní strategie</h3>
            <p class="black"> 
            Díky Drtikulovým schopnestem budete moct po Spojeownu vybíjet duše ze slabých a bezbraných bez jakéhokoliv postihu. Však pokud se nebojíte svého gearu, tak se budete moct vrhnout i na stejně silné oponenty. 
            </p>
            <p class="black">Drtikulovo Q:Drtivý šleh poskytuje dostatečnou útočnou sílu na hromadné drcení bezbranných cílů a v kombinací s ultimátním spellem: SchoolCalibur se stává drtikul v podstatě nezastavitelný, protože Drtikul dokáže zabit většinu ochránců slabých a bezbranných na jedno použití ultimátního spellu.</p>
            <p class="black"> Může se stát, že Drtikulova likvidace  obránců bezbranných pomocí ultimátky nemusí být dokonalá, a nebo se může stát to, že bezbranné brání i lesapání. Tím se všask Drtikul nenechá zastrašit, protože ani nemůže... Protože Drtikulova pasivní schopnost způsobuje záchvaty drcení slabých cílů. Proti případnům obráncům může Drtikul použit své zbylé schopnosti W:Štít úspěchu a samozřejmě E:Přihřátý obránce.</p>
            <div class="content">
            <h2>Představení Drtikulových schopností.</h2>
            <div class="basic">
                <img onclick="switchSpell('p')" class="basicImg" src="images/bezhlavy_utok.png" width="100" height="100" alt="pasivní schopnost"/>
            </div>
            <div class="basic"> 
                <img onclick="switchSpell('q')"  class="basicImg" src="images/drtivy_sleh.jpg" width="100" height="100" alt="drtivy_sleh"/>
            </div>
            <div class="ultimate">
                <img onclick="switchSpell('r')"  class="ultimateImg" src="images/schoolcalubir.jpg" width="150" height="150" alt="schoolcalubir"/>


            </div>
            <div class="basic">
                <img onclick="switchSpell('w')" class="basicImg" src="images/stit_uspechu.jpg" width="100" height="100" alt="stit_uspechu"/>

            </div>
            <div class="basic">
                <img onclick="switchSpell('e')" class="basicImg" src="images/prihraty_ochrance.jpg" width="100" height="100" alt="prihraty_ochrance"/>

            </div>
            
            <div id="popis">
                <h4>Více informací</h4>
                <p>Naklikni jeden ze spellů pro více informací. Však pokud neznáš Drtikulův příběh, tak nepokračuj.</p>
            </div>
            </div>
            </div>
                </div>
        </div>
        
        <script>
        function switchSpell(spell)
        {
            switch(spell) {
  case "p":
    document.getElementById("popis").innerHTML="<h4>Pasivní Schopnost: Drtikul</h4><p>Drtikul se bezhlavě vrhá po každém bezbraném cíly, který zahledné ba i jen koutkem oka.</p>";
    break;
  case "q":
    document.getElementById("popis").innerHTML="<h4>Drtikulovo Q: Drtivý šleh</h4><p>Drtikul se rozmáchne svou mohutnou rukou a rozdrtí všechny koule, které mu stojí v cestě.</p>";
    break;
    case "w":
    document.getElementById("popis").innerHTML="<h4>Drtikulovo W: Štít Úspěchu</h4><p>Drtikul se schová za svůj mohutný štít a nechá ho absorbovat veškeré nedostatečné, které jsou na něj během maturity vyslány.</p>";
    break;
  case "e":
    document.getElementById("popis").innerHTML="<h4>Drtikulovo E: Přihřátý Obránce</h4><p>Drtikul si zavolá na pomoc svého spolužáka, který mu začne masírovat záda a šeptat do ouška líbivá slůvka. To Drtikula nabudí, a zvýší se tím tak jeho mužná síla.</p>";
    break;
    case "r":
    document.getElementById("popis").innerHTML="<h4>Drtikulova ultimátní schopnost: SchoolCalibur</h4><p>Legendární školní meč, který poslouchá pouze Drtikula. Pokud je meč zavolán, umírá 90% učitelské populace.</p>";
    break;
  default:
    document.getElementById("popis").innerHTML="<h4></h4><p>Naklikni jeden ze spellů pro více informací.</p>";
}
        }
        
        </script>
        <?php
        // put your code here
        ?>
    </body>
</html>
