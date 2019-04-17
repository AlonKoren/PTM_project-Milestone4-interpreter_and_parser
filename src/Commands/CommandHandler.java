package Commands;

import Client.Client;
import Server.Server;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class CommandHandler
{
    private Server server;
    private Client client;
    private Map<String, String> symbolTable;
    private Map<String, Supplier<Command>> commandsSupplierMap;

    public CommandHandler(Server server, Client client, Map<String, String> symbolTable)
    {
        this.server = server;
        this.client = client;
        this.symbolTable = symbolTable;
        this.commandsSupplierMap = new ConcurrentHashMap<>();
        commandsSupplierMap.put("openDataServer", () -> new OpenDataServerCommand(this.server));
        commandsSupplierMap.put("connect", () -> new ConnectToClientCommand(this.client));
        commandsSupplierMap.put("var", () -> new VariableDeclarationCommand(this.symbolTable));
        commandsSupplierMap.put("=", () -> new VariableAssignmentCommand(this.symbolTable));
        commandsSupplierMap.put("while", () -> new LoopCommand(this.server, this));
        commandsSupplierMap.put("print", () -> new PrintCommand(this.server));
    }

    public Command getCommand(String commandKey)
    {
        Supplier<Command> commandSupplier = commandsSupplierMap.getOrDefault(commandKey,commandsSupplierMap.get("="));
        return commandSupplier.get();
    }
}
