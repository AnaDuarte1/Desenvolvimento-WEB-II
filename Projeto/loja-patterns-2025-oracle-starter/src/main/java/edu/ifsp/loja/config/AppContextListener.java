package edu.ifsp.loja.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.ifsp.loja.factory.DAOFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties props = new Properties();
        String banco = null;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("/database.properties")) {
            if (input != null) {
                props.load(input);
                banco = props.getProperty("banco");

                if (banco == null || banco.isBlank()) {
                    throw new RuntimeException("Propriedade 'banco' não encontrada no arquivo.");
                }
            } else {
                throw new RuntimeException("Arquivo de configuração 'database.properties' não encontrado.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo de configuração de banco.", e);
        }

        DAOFactory factory = DAOFactory.getFactory(banco);
        sce.getServletContext().setAttribute("DAOFactory", factory);
    }
}
