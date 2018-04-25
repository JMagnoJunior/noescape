# NoEscape
A small game that can show a bit about software architecture.

### Introduction

This is a simples project, but it has some nice concepts implemented. 

I am not using any framework or external tools for the core of this project. The exception are: Mockito, for tests, and Gson for a beautiful and readable json file.

### Layers and Project Structure

This project is divided in layers:
 * Model Layer -> (br.com.magnojr.noescape.service and br.com.magnojr.noescape.model );
 * View Layer -> (br.com.magnojr.noescape.view and br.com.magnojr.noescape.presenter );
 * Repository -> layer (br.com.magnojr.noescape.repository);

The model layer has two package:
 * br.com.magnojr.noescape.service : It has the responsability to orquestrate and invoke all the object from the model package. It describe the process;
 * br.com.magnojr.noescape.model : It has all the business rules from this application;

The model layer is the most important layer of any aplication. It has to be totally decoupled from the other layers, fully tested (unity test) and It shoud not have any external dependencies. This layer should describe my domain like a map describing a region. Furthermore,  a change in the model layer could impact some other layers, but never a change in some other layer should affect the model layer.

This project is structured with a complete decopled business model. 
In this project the repository (repository package) and the view layer (view and presenter packages) are plugged to the application model and they can be changed with no impact on the model layer. As a result we have a model clean and easy to understand.

The view layer has two package: 
  * br.com.magnojr.noescape.presenter : It has the responsability to talk to the mode layer and convert this model in a useful information to the view package;
  * br.com.magnojr.noescape.view : it just render the screen based on the information in the presenter package;

The repository layer:
  * br.com.magnojr.noescape.repository : It has the function to persist and to recover data from any source.
  
### How to test

There is a executable .jar file for the current version inside the /target directory

``` java -jar /target/NoEscape.jar ```
  
  
  
