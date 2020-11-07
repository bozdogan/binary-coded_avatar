package com.boraozdogan.binarycodedavatar.impl.conf;

import com.boraozdogan.binarycodedavatar.impl.Constants;
import com.boraozdogan.binarycodedavatar.interfaces.FileReader;
import com.boraozdogan.binarycodedavatar.interfaces.conf.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

public class ConfigurationFileReader implements FileReader<Configuration>
{
    private ConfigurationFileType configutationFileType;

    private AbstractConfiguration targetConfiguration;

    public ConfigurationFileReader(ConfigurationFileType configutationFileType)
    {
        this.configutationFileType = configutationFileType;
    }

    @Override
    public void setTarget(Configuration targetConfiguration)
    {
        if(targetConfiguration instanceof AbstractConfiguration)
            this.targetConfiguration = (AbstractConfiguration) targetConfiguration;
    }

    @Override
    public void load(Path f)
    {
        if(this.targetConfiguration == null)
            throw new NoTargetConfigurationSetWhatAmISupposedToDoNowException();

        switch(this.configutationFileType)
        {
            case RegularConfiguration:
                try
                {
                    List<String> lines = Files.readAllLines(f);
                    for(String line: lines)
                    {
                        String[] tokens = line.split("=", 2);
                        String configurationKey = tokens[0].strip();
                        String configurationValue = tokens[1].strip();
                        if(configurationValue.charAt(0) == '"' && configurationValue.charAt(configurationValue.length()-1) == '"')
                            configurationValue = configurationValue.substring(1, configurationValue.length()-1);

                        targetConfiguration.configurationContainer.put(configurationKey, configurationValue);
                    }

                }
                catch(NoSuchFileException e)
                {
                    loadDefaults();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }

                break;

            default:
                throw new ConfigurationFileTypeNotSupportedException();
        }
    }

    private void loadDefaults()
    {
        targetConfiguration.configurationContainer.put("DEBUG_bit_matrix_printer_zero_character", Constants.DEBUG_BIT_MATRIX_PRINTER_ZERO_CHAR);
        targetConfiguration.configurationContainer.put("DEBUG_bit_matrix_printer_one_character", Constants.DEBUG_BIT_MATRIX_PRINTER_ONE_CHAR);
    }
}
