package HomeAppliances_Demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JD implements Switch{
    private String name;
    private boolean status;

    @Override

    public void press(){
        this.status=!this.status;
    }
}
