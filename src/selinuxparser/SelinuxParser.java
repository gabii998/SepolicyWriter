/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selinuxparser;

import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class SelinuxParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //allow fsck block_device:blk_file { read open write ioctl};
        String scontext,tcontext,tclass;
        String denial="avc:  denied  { read } for  uid=0 pid=330 comm=\"fsck.f2fs\" name=\"mmcblk0p48\" dev=\"tmpfs\" ino=11647 scontext=u:r:fsck:s0 tcontext=u:object_r:block_device:s0 tclass=blk_file permissive=1";
        denial=JOptionPane.showInputDialog("Inserte denial");
        String[] splitedUno= denial.split("scontext=u:r:");
        String[] splitedDos=splitedUno[1].split(":s0");
        String[] splitedTres=splitedDos[1].split("tcontext=u:object_r:");
        String[] splitedCuatro=splitedDos[2].split("tclass=");
        splitedCuatro=splitedCuatro[1].split(" permissive=1");
        
        splitedUno=splitedUno[0].split(" } for");
        splitedUno=splitedUno[0].split("denied  \\{ ");
        /*Sólo para propósitos de depuración*/
        for (int i = 0; i < splitedUno.length; i++) {
            System.out.println("SplitedUno "+i+"= "+ splitedUno[i]);
        }
        for (int i = 0; i < splitedDos.length; i++) {
            System.out.println("SplitedDos "+i+"= "+ splitedDos[i]);
        }
        for (int i = 0; i < splitedTres.length; i++) {
            System.out.println("SplitedTres "+i+"= "+ splitedTres[i]);
        }
        for (int i = 0; i < splitedCuatro.length; i++) {
            System.out.println("SplitedCuatro "+i+"= "+ splitedCuatro[i]+" { }");
        }
       String fixed="allow "+splitedDos[0]+" "+splitedTres[1]+":"+splitedCuatro[0]+" { "+splitedUno[1]+" };";
        System.out.println(fixed);
    }
    
}
