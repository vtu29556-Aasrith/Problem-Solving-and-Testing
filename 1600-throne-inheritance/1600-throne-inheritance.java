import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ThroneInheritance {

    private final String king;
    private final Map<String, List<String>> children;
    private final Set<String> dead;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.children = new HashMap<>();
        this.dead = new HashSet<>();
        this.children.put(kingName, new ArrayList<>());
    }
    
    public void birth(String parentName, String childName) {
        this.children.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
        this.children.put(childName, new ArrayList<>());
    }
    
    public void death(String name) {
        this.dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(String current, List<String> order) {
        if (!dead.contains(current)) {
            order.add(current);
        }
        for (String child : children.getOrDefault(current, new ArrayList<>())) {
            dfs(child, order);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */