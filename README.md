# ReistijdenAPI
App Engine API voor reistijden verkeerscentrum

Deploying this to the Google App Engine enables the following API:

##Knooppunten
Knooppunten: List all knooppunten in the Flemish verkeerscentrum network  
/knooppunten  
[{"name":" A12 Meise","verkeerscentrumName":" A12 Meise","xCo":50.943906,"yCo":4.324303},...]  
<strong>Name:</strong> Internal API Name  
<strong>verkeerscentrumName:</strong> Name of knooppunt on the verkeerscentrum. This is also the name that should be displayed in an application.  
  The Name and verkeerscentrumName usually are consistent, however can differ when there are two ways to reach one knooppunt (eg. Ring Antwerpen Noord vs Zuid).  
<strong>xCo,yCo:</strong> coordinates in WGS 84 Web Mercator  

##Knooppunt
Knooppunt: Find the nearest knooppunt from a specific location  
/knooppunt?x=50,y=4  
<strong>x:</strong> xCo in WGS 84  
<strong>y:</strong> yCo in WGS 84  
{"name":"Halle","verkeerscentrumName":"Halle","xCo":50.724021,"yCo":4.269115}  
<strong>Name:</strong> Internal API Name  
<strong>verkeerscentrumName:</strong> Name of knooppunt on the verkeerscentrum  
<strong>xCo,yCo:</strong> coordinates in WGS 84 Web Mercator  

##Reistijden
Reistijden: find the travelling time between two nodes  
/reistijden?nodes=[node1,node2]  
<strong>nodes:</strong> a json array of start and end node, node1 and node2 should be from the knooppunt(en) API  
[{"evolutie":"stabiel","filevrij":2,"from":{"name":"West","verkeerscentrumName":"West","xCo":51.214346,"yCo":4.357262},"nu":2,"to":{"name":"Centrum","verkeerscentrumName":"Centrum","xCo":51.19521,"yCo":4.385868},"vertraging":0},...]  
<strong>evolutie:</strong> Stabiel, Stijgend, Dalend: indication of future traffic on this section  
<strong>filevrij:</strong> Traveltime on section when there is no traffic  
<strong>from:</strong> Start node for section  
<strong>nu:</strong> Traveltime at the moment  
<strong>to:</strong> end node for section  
<strong>vertraging:</strong> Delay on the section now  
