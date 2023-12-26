package su.itpro.photogram.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import su.itpro.photogram.dao.ComplaintDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.ComplaintCreateMapper;
import su.itpro.photogram.mapper.ComplaintDtoMapper;
import su.itpro.photogram.mapper.ComplaintFindDtoMapper;
import su.itpro.photogram.mapper.ComplaintMapper;
import su.itpro.photogram.model.dto.ComplainFindDto;
import su.itpro.photogram.model.dto.ComplaintActionDto;
import su.itpro.photogram.model.dto.ComplaintCreateDto;
import su.itpro.photogram.model.dto.ComplaintDto;
import su.itpro.photogram.model.dto.ComplaintSearchDto;
import su.itpro.photogram.model.dto.PostUpdateDto;
import su.itpro.photogram.model.entity.Complaint;
import su.itpro.photogram.model.enums.PostStatus;
import su.itpro.photogram.service.ComplaintService;
import su.itpro.photogram.service.PostService;

public class ComplaintServiceImpl implements ComplaintService {

  private static final ComplaintService INSTANCE = new ComplaintServiceImpl();

  private final ComplaintDao complaintDao;
  private final PostService postService;
  private final ComplaintCreateMapper complaintCreateMapper;
  private final ComplaintFindDtoMapper complaintFindDtoMapper;
  private final ComplaintDtoMapper complaintDtoMapper;
  private final ComplaintMapper complaintMapper;



  private ComplaintServiceImpl() {
    complaintDao = DaoFactory.INSTANCE.getComplaintDao();
    postService = PostServiceImpl.getInstance();
    complaintCreateMapper = ComplaintCreateMapper.getInstance();
    complaintFindDtoMapper = ComplaintFindDtoMapper.getInstance();
    complaintDtoMapper = ComplaintDtoMapper.getInstance();
    complaintMapper = ComplaintMapper.getInstance();
  }

  public static ComplaintService getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean exists(UUID accountId, UUID postId) {
    return complaintDao.exist(accountId, postId);
  }

  @Override
  public List<ComplaintDto> search(ComplaintSearchDto searchDto) {

    ComplainFindDto findDto = complaintFindDtoMapper.mapFrom(searchDto);
    List<Complaint> complaints = complaintDao.findAllBy(findDto);
    List<ComplaintDto> complaintDtoList = new ArrayList<>();
    complaints.forEach(complaint -> complaintDtoList.add(complaintDtoMapper.mapFrom(complaint)));
    return complaintDtoList;
  }

  @Override
  public void create(ComplaintCreateDto createDto) {

    Complaint complaint = complaintCreateMapper.mapFrom(createDto);
    complaintDao.save(complaint);
  }

  @Override
  public void action(ComplaintActionDto actionDto) {

    switch (actionDto.status()) {
      case APPROVED -> {
        postService.update(new PostUpdateDto(actionDto.postId(), PostStatus.BLOCKED, null));
        complaintDao.update(complaintMapper.mapFrom(actionDto));
      }
      case REJECTED -> {
        complaintDao.update(complaintMapper.mapFrom(actionDto));
      }
      default -> {
        throw new IllegalStateException();
      }
    }
  }

  @Override
  public void deleteByPost(UUID postId) {
    complaintDao.deleteByPost(postId);
  }

}