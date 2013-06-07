# BGSShop

## Installazione

### Prerequisiti

* Java 7
* Eclipse for Java EE Developers
* Tomcat
* EGit (incluso in Eclipse EE)
* [m2eclipse](http://eclipse.org/m2e/)
* [m2e-wtp](http://www.eclipse.org/m2e-wtp/)

### Importare il Progetto

Da Eclipse:

1. `File -> Import -> Check out Maven Projects from SCM`
2. Cliccare sul link in basso a destra "Find more SCM connectors in the m2e".
3. Installare m2e-egit e riavviare Eclipse
4. Inserire nel campo SCM URL l'indirizzo del repository:
  * Per un accesso sola lettura: `git://github.com/biagiocaldararo/siw.git`
  * Per contribuire al codice: `https://github.com/biagiocaldararo/siw.git` (Richiedere un account di Github)

Alternativamente si possono ottenere i sorgenti del Progetto, senza installare m2e-egit, tramite git.
Basterà eseguire da linea di comando:
    
    $ git clone https://github.com/biagiocaldararo/siw.git

Per clonare il progetto nella directory corrente. A questo punto per importare il progetto in Eclipse
basterà fare `Import -> Existing Maven Projects` e selezionare la directory del progetto appena clonato.

## Contribuire

Occorre configurare eclipse nel modo seguente:

* `Window -> Preferences -> General -> Workspace`
  - Assicurarsi che "Text file encoding" sia impostato su "UTF-8"
  - Assicurarsi che "New text file line delimiter" sia impostato su "Unix"
