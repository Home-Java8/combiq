package ru.atott.combiq.data.commands;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.ExitShellRequest;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class ShellExitCommands implements CommandMarker {

    @CliCommand(value={"exit", "quit"}, help="Exits the shell")
    public ExitShellRequest quit() {
        return ExitShellRequest.NORMAL_EXIT;
    }
}
