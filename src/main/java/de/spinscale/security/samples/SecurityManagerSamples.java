package de.spinscale.security.samples;

import java.io.FilePermission;
import java.lang.reflect.ReflectPermission;
import java.net.SocketPermission;
import java.security.Permission;
import java.security.Policy;
import java.security.ProtectionDomain;

public class SecurityManagerSamples {

    public static void main(String[] args) throws Exception {
        Policy.setPolicy(new CustomPolicy(
                // samples 1-3
                new ReflectPermission("suppressAccessChecks"),
                new RuntimePermission("accessDeclaredMembers"),

                // Sample 3
                new RuntimePermission("accessClassInPackage.sun.misc"),

                // sample 4
                new SocketPermission("localhost", "resolve,connect"),
                new SocketPermission("127.0.0.1:9999", "resolve,connect"),
                new FilePermission("/etc/passwd", "read"),

                // sample 5
                new FilePermission( "/bin/ls", "execute")
        ));
        System.setSecurityManager(new SecurityManager());
        Sample01.main(new String[0]);

        Sample02.main(new String[0]);

        Sample03.main(new String[0]);

        Sample04.main(new String[0]);

        Sample05.main(new String[0]);
    }

    public static final class CustomPolicy extends Policy {
        private final Permission[] permissions;

        CustomPolicy(Permission ... permissions) {
            this.permissions = permissions;
        }

        @Override
        public boolean implies(final ProtectionDomain domain, final Permission permission) {
            for (Permission p : permissions) {
                if (p.getName().equals(permission.getName()) &&
                        p.getClass().getName().equals(permission.getClass().getName())) {
                    return true;
                }
            }

            return false;
        }
    }
}
