package unicam.dreamteam.presentation.mapper;

public interface IMapper<O, D>{
    D toResponse(O Object);
}
