/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radiusclients;

import java.io.IOException;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.AccountingRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusClient;
import org.tinyradius.util.RadiusException;
import  java.net.InetAddress;

/**
 *
 * @author ZORO
 */
public class Radiusclients {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws org.tinyradius.util.RadiusException
     */
    public static void main(String[] args) throws IOException, RadiusException {
        // TODO code application logic here
		RadiusClient rc = new RadiusClient("192.168.140.132", "mikrotest");
		// 1. Send Access-Request
		AccessRequest ar = new AccessRequest("sqltest", "testpwd");
		ar.setAuthProtocol(AccessRequest.AUTH_PAP); // or AUTH_CHAP
		ar.addAttribute("NAS-Identifier", "this.is.my.nas-identifier.de");
		ar.addAttribute("NAS-IP-Address", "192.168.140.132");
		ar.addAttribute("Service-Type", "Login-User");
//		ar.addAttribute("WISPr-Redirection-URL", "http://www.sourceforge.net/");
		ar.addAttribute("WISPr-Location-ID", "net.sourceforge.ap1");
		
		System.out.println("Packet before it is sent\n" + ar + "\n");
		RadiusPacket response = rc.authenticate(ar);
		System.out.println("Packet after it was sent\n" + ar + "\n");
		System.out.println("Response\n" + response + "\n");

		// 2. Send Accounting-Request
		AccountingRequest acc = new AccountingRequest("mw", AccountingRequest.ACCT_STATUS_TYPE_START);
		acc.addAttribute("Acct-Session-Id", "1234567890");
		acc.addAttribute("NAS-Identifier", "this.is.my.nas-identifier.de");
		acc.addAttribute("NAS-Port", "1812");
	
		System.out.println(acc + "\n");	
		response = rc.account(acc);
		System.out.println("Response: " + response);
		
		rc.close();
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
