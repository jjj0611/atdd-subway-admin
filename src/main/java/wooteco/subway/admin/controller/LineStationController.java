package wooteco.subway.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import wooteco.subway.admin.dto.LineStationCreateRequest;
import wooteco.subway.admin.dto.StationResponse;
import wooteco.subway.admin.service.LineService;

@RestController
public class LineStationController {

    private final LineService lineService;

    public LineStationController(LineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping("/lines/{lineId}/stations")
    public List<StationResponse> getStationResponse(@PathVariable Long lineId) {
        return lineService.findStationsByLineId(lineId);
    }

    @PostMapping("/lines/{lineId}/stations")
    public ResponseEntity addLineStation(@PathVariable Long lineId,
        @RequestBody LineStationCreateRequest lineStationCreateRequest) {
        lineService.addLineStation(lineId, lineStationCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/lines/{lineId}/stations/{stationId}")
    public ResponseEntity deleteLineStation(@PathVariable Long lineId,
        @PathVariable Long stationId) {
        lineService.removeLineStation(lineId, stationId);
        return ResponseEntity.noContent().build();
    }
}
