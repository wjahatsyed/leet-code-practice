package Network_Specific_Algorithms;

import java.util.*;

/**
 * Network configuration validator using state machine
 */
public class NetworkConfigValidator {
    private enum State {
        INITIAL, INTERFACE, ACL, ROUTING, VALID, ERROR
    }

    private State currentState;
    private Map<State, Map<String, State>> transitions;
    private Set<String> validatedSections;
    private List<String> errors;

    public NetworkConfigValidator() {
        currentState = State.INITIAL;
        validatedSections = new HashSet<>();
        errors = new ArrayList<>();
        setupTransitions();
    }

    private void setupTransitions() {
        transitions = new HashMap<>();

        //Initialize all states
        for (State state : State.values()) {
            transitions.put(state, new HashMap<>());
        }

        // Define transitions
        transitions.get(State.INITIAL).put("interface", State.INTERFACE);
        transitions.get(State.INITIAL).put("acl", State.ACL);
        transitions.get(State.INITIAL).put("routing", State.ROUTING);

        transitions.get(State.INTERFACE).put("interface", State.INTERFACE);
        transitions.get(State.INTERFACE).put("acl", State.ACL);
        transitions.get(State.INTERFACE).put("routing", State.ROUTING);
        transitions.get(State.INTERFACE).put("end", State.VALID);

        transitions.get(State.ACL).put("interface", State.INTERFACE);
        transitions.get(State.ACL).put("acl", State.ACL);
        transitions.get(State.ACL).put("routing", State.ROUTING);
        transitions.get(State.ACL).put("end", State.VALID);

        transitions.get(State.ROUTING).put("interface", State.INTERFACE);
        transitions.get(State.ROUTING).put("acl", State.ACL);
        transitions.get(State.ROUTING).put("routing", State.ROUTING);
        transitions.get(State.ROUTING).put("end", State.VALID);
    }

    /**
     * Process a configuration command
     *
     * @param command The command to process
     * @return True if command is processed successfully, false otherwise
     */
    public boolean processCommand(String command) {
        String type = extractCommandType(command);

        if (type == null) {
            errors.add("Unknown command type: " + command);
            currentState = State.ERROR;
            return false;
        }

        // Check if transition is valid
        if (!transitions.get(currentState).containsKey(type)) {
            errors.add("Invalid transition from " + currentState + " with command type " + type);
            currentState = State.ERROR;
            return false;
        }

        // Update state
        currentState = transitions.get(currentState).get(type);

        // Validate command content based on type
        boolean isValid = validateCommandContent(type, command);
        if (!isValid) {
            currentState = State.ERROR;
            return false;
        }

        // Add to validated sections
        if (!type.equals("end")) {
            validatedSections.add(type);
        }

        return true;
    }

    /**
     * Check if the configuration is valid
     * @return True if configuration is valid, false otherwise
     */
    public boolean isValid() {
        return currentState == State.VALID;
    }

    /**
     * Get any validation errors
     * @return List of error messages
     */
    public List<String> getErrors() {
        return errors;
    }


    private String extractCommandType(String command) {
        command = command.toLowerCase();
        if (command.startsWith("interface ")) return "interface";
        if (command.startsWith("acl ")) return "acl";
        if (command.startsWith("routing")) return "routing";
        if (command.startsWith("end ")) return "end";
        return null;
    }

    private boolean validateCommandContent(String type, String command) {
        switch (type) {
            case "interface":
                return validateInterfaceCommand(command);
            case "acl":
                return validateAclCommand(command);
            case "routing":
                return validateRoutingCommand(command);
            case "end":
                return true;
            default:
                errors.add("Unknown command type: " + type);
                return false;
        }
    }

    private boolean validateInterfaceCommand(String command) {
        // Sample validation logic for interface commands
        if (!command.contains("ip address")) {
            errors.add("Interface configuration missing IP address: " + command);
            return false;
        }
        return true;
    }

    private boolean validateAclCommand(String command) {
        // Sample validation logic for ACL commands
        if (!command.contains("permit") && !command.contains("deny")) {
            errors.add("ACL configuration missing permit or deny action: " + command);
            return false;
        }
        return true;
    }

    private boolean validateRoutingCommand(String command) {
        // Sample validation logic for routing commands
        if (!command.contains("network")) {
            errors.add("Routing configuration missing network specification: " + command);
            return false;
        }
        return true;
    }

}
