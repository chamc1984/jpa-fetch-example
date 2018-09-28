package xyz.chamc.jpafetchexample.app.fetchupdate;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class ManydataDto {
    private Integer id;
    private String username;
    private String checkflg;
}
