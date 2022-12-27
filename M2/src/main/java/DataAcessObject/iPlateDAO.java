package DataAcessObject;

import java.util.List;

import Entidades.Prato;

public interface iPlateDAO {
     List<Prato> obterTodosOsPratosJSON();
     List<Prato> obterTodosOsPratosXML();
}
