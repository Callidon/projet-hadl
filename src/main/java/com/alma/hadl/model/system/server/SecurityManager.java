package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Composant SecurityManager qui représnte un gestionnaire de sécurité.
 * Il est chargé de valider les mots de passe et d'authorise une requête si l'état de la base de données le permet.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class SecurityManager extends Component {
    private static final Logger logger = Logger.getLogger(SecurityManager.class.getName());

    private final String refString = "my-great-password";
    private byte[] refPassword;
    private final String salt;

    public SecurityManager(ProvidedPortComponent<String> sendAuthAnswer, RequiredPortComponent<byte[]> receiveAuthRequest,
                           ProvidedPortComponent<String> sendSecurityRequest, RequiredPortComponent<String> receiveSecurityAnswer) {
        super("SecurityManager", Arrays.asList(sendAuthAnswer, receiveAuthRequest, sendSecurityRequest, receiveSecurityAnswer));

        // generate a random salt for more security
        SecureRandom random = new SecureRandom();
        salt = new BigInteger(130, random).toString();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // add salt then hash reference password
            md.update(salt.getBytes());
            refPassword = md.digest(refString.getBytes());
        } catch (NoSuchAlgorithmException e) {
            logger.warning(e.getMessage());
        }

        // Listen for incoming Authorization requests
        receiveAuthRequest.subscribe(data -> {
            try {
                // decode the provided password, then compare it with the reference
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(salt.getBytes());
                byte[] password = md.digest(data);
                if(MessageDigest.isEqual(refPassword, password)) {
                    sendSecurityRequest.send("database available ?");
                } else {
                    sendAuthAnswer.send("access denied, incorrect password");
                }
            } catch (NoSuchAlgorithmException e) {
                logger.warning(e.getMessage());
            }
        });

        // Listen for incoming security's answer from database
        receiveSecurityAnswer.subscribe(data -> {
            if("ok".equals(data)) {
                sendAuthAnswer.send("ok");
            } else {
                sendAuthAnswer.send(data);
            }
        });

    }
}
