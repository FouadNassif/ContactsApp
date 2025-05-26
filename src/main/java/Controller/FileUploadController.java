package Controller;

import Model.Contact;
import Model.Group;
import UsefulFunctions.ErrorFunctions;
import UsefulFunctions.FileFunctions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.CONTACT_FILE;
import static java3.nfa035_fouadnassif_2339t.GlobalVariables.GROUP_FILE;
import javax.swing.JFileChooser;
import view.FileUploadView;

public class FileUploadController {

    private FileUploadView fileUploadView;
    private JFileChooser fileChooser = new JFileChooser();
    private int result;

    public FileUploadController(FileUploadView view) {
        fileUploadView = view;

        fileUploadView.getImportBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result = fileChooser.showOpenDialog(fileUploadView);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    importFile(selectedFile);
                }
            }
        });
        fileUploadView.getExportBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportFile(CONTACT_FILE.getAbsolutePath());
            }

        });

    }

    private void importFile(File file) {
        if (file.getName().endsWith(".obj") && file.isFile()) {
            Object firstObject = readFirstObject(file);
            if (firstObject instanceof Contact) {
                saveContactFromFile(file);
            } else if (firstObject instanceof Group) {
                saveGroupFromFile(file);
            }
        } else {
            ErrorFunctions.showErrorDialogMessage("Can't import this File.\nMust ends with .obj", "Unsupported File");
        }
    }

    public Object readFirstObject(File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void saveContactFromFile(File file) {
        ArrayList<Contact> allContacts = FileFunctions.emptyFileInList(CONTACT_FILE);

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                try {
                    Contact contact = (Contact) ois.readObject();
                    allContacts.add(contact);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
            FileFunctions.saveToFile(allContacts, CONTACT_FILE);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    public void saveGroupFromFile(File file) {
        ArrayList<Group> importedGroups = new ArrayList<>();
        ArrayList<Group> existingGroups = FileFunctions.emptyFileInListGroup(GROUP_FILE);

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                try {
                    Group imported = (Group) ois.readObject();
                    if (!imported.getName().equalsIgnoreCase("No groups")) {
                        boolean exists = false;
                        for (Group g : existingGroups) {
                            if (g.getName().equalsIgnoreCase(imported.getName())) {
                                exists = true;
                                break;
                            }
                        }
                        if (exists) {
                            Group newGroup = new Group(imported);
                            newGroup.setName(imported.getName() + " (IMPORTED)");
                            importedGroups.add(newGroup);
                        } else {
                            importedGroups.add(imported);
                        }
                    }

                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();

            existingGroups.addAll(importedGroups);
            FileFunctions.saveToFileGroup(existingGroups, GROUP_FILE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportFile(String sourceFilePath) {
        File sourceFile = new File(sourceFilePath);

        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist.");
            return;
        }

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File destFile = fileChooser.getSelectedFile();

            String destPath = destFile.getAbsolutePath();
            if (!destPath.toLowerCase().endsWith(".obj")) {
                destPath += ".obj";
                destFile = new File(destPath);
            }

            try {
                copyFile(sourceFile, destFile);
                System.out.println("File exported successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyFile(File source, File dest) throws IOException {
        try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
