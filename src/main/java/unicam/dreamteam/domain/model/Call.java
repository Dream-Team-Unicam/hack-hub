package unicam.dreamteam.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calls")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String linkMeeting;

    @Column(nullable = false)
    private LocalDateTime dataOra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "richiesta_supporto_id", nullable = false)
    private RichiestaSupporto richiestaSupporto;

    public Call() {}

    public Long getId() { return id; }
    public String getLinkMeeting() {
        return linkMeeting;
    }
    public void setLinkMeeting(String linkMeeting) {
        this.linkMeeting = linkMeeting;
    }
    public LocalDateTime getDataOra() {
        return dataOra;
    }
    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }
    public RichiestaSupporto getTicket() {
        return richiestaSupporto;
    }
    public void setRichiestaSupporto(RichiestaSupporto richiestaSupporto) {
        this.richiestaSupporto = richiestaSupporto;
    }
}
