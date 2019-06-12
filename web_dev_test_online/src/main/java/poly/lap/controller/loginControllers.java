/*
**======= Abouts us =======**
* System name : assigment5
* Verstion    : 1.0
* Create date : Mar 9, 2019 11:41:34 AM
* Description : Create by Linh Noob
* Copyright © 2019 Noob.com.All rights reserved.
**=========================**
*/
package poly.lap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import poly.lap.models.DepartsModel;
import poly.lap.models.RecordsModel;
import poly.lap.models.StaffsModel;
import poly.lap.models.UserModel;
import poly.lap.services.DepartsExService;
import poly.lap.services.RecordsExService;
import poly.lap.services.StaffsExService;
import poly.lap.services.UsersExService;

// TODO: Auto-generated Javadoc
/**
 * The Class loginControllers.
 */
@Controller
@RequestMapping("/DangNhap")
public class loginControllers {

    /** The users ex service. */
    @Autowired
    private UsersExService usersExService;

    /** The staffs ex service. */
    @Autowired
    private StaffsExService staffsExService;

    /** The departs ex service. */
    @Autowired
    private DepartsExService departsExService;

    /** The records ex service. */
    @Autowired
    private RecordsExService recordsExService;

    /**
	 * Login.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("login", new UserModel());
        return "DangNhap/login";
    }


    /**
	 * Login.
	 *
	 * @param model    the model
	 * @param username the username
	 * @param password the password
	 * @return the string
	 */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<UserModel> user = usersExService.findById(username);
        System.out.println(user);
        if (username.equals(user.get().getUsername()) && password.equals(user.get().getPassword())) {
            System.out.println("hiện thị............................");
            return "redirect:/DangNhap/addNhanVien";
        } else {
            System.out.println("Sai ..............................");
            return "DangNhap/login";
        }
    }
  
    /**
	 * Adds the.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "/addNhanVien", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("staff", new StaffsModel());
        return "DangNhap/addNhanVien";
    }


    /**
	 * Inits the binder.
	 *
	 * @param request the request
	 * @param binder  the binder
	 * @throws ServletException the servlet exception
	 */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException{
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
 
    /**
	 * Adds the.
	 *
	 * @param model the model
	 * @param staff the staff
	 * @param file  the file
	 * @return the string
	 */
    @RequestMapping(value = "/addNhanVien", method = RequestMethod.POST)
    public String add( ModelMap model, @Valid @ModelAttribute("DangNhap") StaffsModel staff,@RequestParam("photo") MultipartFile file) {

        staffsExService.save(staff);

        return "redirect:/DangNhap/addNhanVien";
    }


    /**
	 * List.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("DangNhap", staffsExService.findAll());

        return "DangNhap/list";
    }


    /**
	 * Edits the NV.
	 *
	 * @param model    the model
	 * @param staffsID the staffs ID
	 * @return the string
	 */
    @RequestMapping(value = "/EditNhanVien/{staffsID}", method = RequestMethod.GET)
    public String editNV(ModelMap model,
            @PathVariable(name = "staffsID") String staffsID) {
        Optional<StaffsModel> staffModel
                = staffsExService.findById(staffsID);
        StaffsModel staff = new StaffsModel();
        if (staffModel.isPresent()) {
            staff.setName(staffModel.get().getName());
            staff.setGender(staffModel.get().isGender());
            staff.setBirthday(staffModel.get().getBirthday());
            staff.setPhoto(staffModel.get().getPhoto());
            staff.setEmail(staffModel.get().getEmail());
            staff.setPhone(staffModel.get().getPhone());
            staff.setSalary(staffModel.get().getSalary());
            staff.setNotes(staffModel.get().getNotes());
            staff.setDepartId(staffModel.get().getDepartId());
            staff.setId(staffModel.get().getId());
        }
        model.addAttribute("staff", staff);
        return "DangNhap/EditNhanVien";
    }

    /**
	 * Edits the NV.
	 *
	 * @param model the model
	 * @param staff the staff
	 * @return the string
	 */
    @RequestMapping(value = "/EditNhanVien", method = RequestMethod.POST)
    public String editNV(ModelMap model, StaffsModel staff) {
        StaffsModel staffModel
                = new StaffsModel();
        staffModel.setName(staff.getName());
        staffModel.setGender(staff.isGender());
        staffModel.setBirthday(staff.getBirthday());
        staffModel.setPhoto(staff.getPhoto());
        staffModel.setEmail(staff.getEmail());
        staffModel.setPhone(staff.getPhone());
        staffModel.setSalary(staff.getSalary());
        staffModel.setNotes(staff.getNotes());
        staffModel.setDepartId(staff.getDepartId());
        staffModel.setId(staff.getId());

        staffsExService.save(staffModel);

        return "redirect:/DangNhap";
    }


    /**
	 * Delete NV.
	 *
	 * @param model    the model
	 * @param staffsID the staffs ID
	 * @return the string
	 */
    @RequestMapping(value = "/delete/{staffsID}", method = RequestMethod.GET)
    public String deleteNV(ModelMap model,
            @PathVariable(name = "staffsID") String staffsID) {
        staffsExService.deleteById(staffsID);
        model.addAttribute("messaage", "The staffs has been delete!!!");

        return "redirect:/DangNhap";
    }


    /**
	 * List depart.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "/listDepart", method = RequestMethod.GET)
    public String listDepart(ModelMap model) {

        model.addAttribute("Depart", departsExService.findAll());

        return "DangNhap/listDepart";
    }

    /**
	 * Adds the depart.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "/AddDepart", method = RequestMethod.GET)
    public String addDepart(ModelMap model) {
        model.addAttribute("depart", new DepartsModel());

        return "DangNhap/AddDepart";
    }

    /**
	 * Adds the depart.
	 *
	 * @param depart        the depart
	 * @param bindingResult the binding result
	 * @param model         the model
	 * @return the string
	 */
    @RequestMapping(value = "/AddDepart", method = RequestMethod.POST)
    public String addDepart(@Valid @ModelAttribute("depart") DepartsModel depart, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("depart", depart);
            return "DangNhap/AddDepart";
        }
        DepartsModel departModel = new DepartsModel();
        departModel.setName(depart.getName());
        departModel.setId(depart.getId());

        departsExService.save(departModel);

        return "redirect:/DangNhap/AddDepart";
    }

    /**
	 * Edits the depart.
	 *
	 * @param model the model
	 * @param id    the id
	 * @return the string
	 */
    @RequestMapping(value = "/EditDepart/{departId}", method = RequestMethod.GET)
    public String editDepart(ModelMap model,
            @PathVariable(name = "departId") String id) {
        Optional<DepartsModel> departModel
                = departsExService.findById(id);
        DepartsModel depart = new DepartsModel();
        if (departModel.isPresent()) {
            depart.setName(departModel.get().getName());
            depart.setId(departModel.get().getId());

        }
        model.addAttribute("departs", depart);
        return "DangNhap/EditDepart";
    }

    /**
	 * Edits the depart.
	 *
	 * @param depart        the depart
	 * @param bindingResult the binding result
	 * @param model         the model
	 * @return the string
	 */
    @RequestMapping(value = "/EditDepart", method = RequestMethod.POST)
    public String editDepart(@Valid @ModelAttribute("depart") DepartsModel depart, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departs", depart);
            return "DangNhap/EditDepart";
        }
        DepartsModel departModel
                = new DepartsModel();
        departModel.setName(depart.getName());
        departModel.setId(depart.getId());

        departsExService.save(departModel);

        return "redirect:/DangNhap/listDepart";
    }

    /**
	 * Delete depart.
	 *
	 * @param model the model
	 * @param id    the id
	 * @return the string
	 */
    @RequestMapping(value = "/delete1/{departId}", method = RequestMethod.GET)
    public String deleteDepart(ModelMap model,
            @PathVariable(name = "departId") String id) {
        departsExService.deleteById(id);
        model.addAttribute("messaage", "The depart has been delete!!!");

        return "redirect:/DangNhap/listDepart";
    }


    /**
	 * List users.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping("/listUser")
    public String listUsers(ModelMap model) {
        List<UserModel> user = new ArrayList<>();
        List<UserModel> modelusers = (List<UserModel>) usersExService.findAll();
        System.out.println(modelusers);
        for (UserModel modeluser : modelusers) {
        	UserModel users = new UserModel();
            System.out.println(user + "............user");
            users.setPassword(modeluser.getPassword());
            users.setFullname(modeluser.getFullname());
            users.setUsername(modeluser.getUsername());
            user.add(users);
        }
        model.addAttribute("User", modelusers);
        System.out.println("go ...................");
        return "DangNhap/listUsers";
    }

    /**
	 * Adds the user.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "/AddUsers", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        model.addAttribute("user", new UserModel());
        return "DangNhap/AddUsers";
    }

    /**
	 * Adds the user.
	 *
	 * @param user          the user
	 * @param bindingResult the binding result
	 * @param model         the model
	 * @return the string
	 */
    @RequestMapping(value = "/AddUsers", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "DangNhap/AddUsers";
        }
        UserModel userModel = new UserModel();
        userModel.setPassword(user.getPassword());
        userModel.setFullname(user.getFullname());
        userModel.setUsername(user.getUsername());
        usersExService.save(userModel);
        return "redirect:/DangNhap/AddUsers";
    }

    /**
	 * Edits the user.
	 *
	 * @param model    the model
	 * @param username the username
	 * @return the string
	 */
    @RequestMapping(value = "/EditUsers/{username}", method = RequestMethod.GET)
    public String editUser(ModelMap model, @PathVariable(name = "username") String username) {
        Optional<UserModel> usersModel
                = usersExService.findById(username);
        UserModel user = new UserModel();
        if (usersModel.isPresent()) {
            user.setPassword(usersModel.get().getPassword());
            user.setFullname(usersModel.get().getFullname());
            user.setUsername(usersModel.get().getUsername());

        }
        model.addAttribute("user", user);
        return "DangNhap/EditUsers";
    }

    /**
	 * Edits the user.
	 *
	 * @param user          the user
	 * @param bindingResult the binding result
	 * @param model         the model
	 * @return the string
	 */
    @RequestMapping(value = "/EditUsers", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "DangNhap/EditUsers";
        }
        UserModel userModel
                = new UserModel();
        userModel.setPassword(user.getPassword());
        userModel.setFullname(user.getFullname());
        userModel.setUsername(user.getUsername());

        usersExService.save(userModel);

        return "redirect:/DangNhap/listUser";
    }

    /**
	 * Delete user.
	 *
	 * @param model    the model
	 * @param username the username
	 * @return the string
	 */
    @RequestMapping(value = "/delete2/{username}", method = RequestMethod.GET)
    public String deleteUser(ModelMap model,
            @PathVariable(name = "username") String username) {
        usersExService.deleteById(username);
        model.addAttribute("messaage", "The user has been delete!!!");

        return "redirect:/DangNhap/listUser";
    }

 
    /**
	 * List record.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping("/listRecord")
    public String listRecord(ModelMap model) {
        List<RecordsModel> record = new ArrayList<>();
        List<RecordsModel> modelrecords = (List<RecordsModel>) recordsExService.findAll();
        System.out.println(modelrecords);

        model.addAttribute("Records", modelrecords);
        System.out.println("Chạy ok ...................");
        return "DangNhap/listRecord";
    }

    /**
	 * Adds the record.
	 *
	 * @param model the model
	 * @return the string
	 */
    @RequestMapping(value = "/AddRecord", method = RequestMethod.GET)
    public String addRecord(ModelMap model) {
        model.addAttribute("records", new RecordsModel());
        return "DangNhap/AddRecord";
    }

    /**
	 * Adds the record.
	 *
	 * @param record the record
	 * @param model  the model
	 * @return the string
	 */
    @RequestMapping(value = "/AddRecord", method = RequestMethod.POST)
    public String addRecord(RecordsModel record, ModelMap model) {
        RecordsModel recordModel = new RecordsModel();
        recordModel.setType(record.isType());
        recordModel.setReason(record.getReason());
        recordModel.setDate(record.getDate());
        recordModel.setStaffsId(record.getStaffsId());
        recordModel.setId(record.getId());
        recordsExService.save(recordModel);
        return "redirect:/DangNhap/AddRecord";
    }

    /**
	 * Edits the record.
	 *
	 * @param model    the model
	 * @param recordID the record ID
	 * @return the string
	 */
    @RequestMapping(value = "/EditRecords/{recordID}", method = RequestMethod.GET)
    public String editRecord(ModelMap model, @PathVariable(name = "recordID") String recordID) {
        Optional<RecordsModel> recordModel
                = recordsExService.findById(recordID);
        RecordsModel record = new RecordsModel();
        if (recordModel.isPresent()) {
            record.setType(recordModel.get().isType());
            record.setReason(recordModel.get().getReason());
            record.setDate(recordModel.get().getDate());
            record.setStaffsId(recordModel.get().getStaffsId());
            record.setId(recordModel.get().getId());
        }
        model.addAttribute("record", record);
        return "DangNhap/EditRecords";
    }

    /**
	 * Edits the record.
	 *
	 * @param model  the model
	 * @param record the record
	 * @return the string
	 */
    @RequestMapping(value = "/EditRecords", method = RequestMethod.POST)
    public String editRecord(ModelMap model, RecordsModel record) {
    	RecordsModel recordModel
                = new RecordsModel();
        recordModel.setType(record.isType());
        recordModel.setReason(record.getReason());
        recordModel.setDate(record.getDate());
        recordModel.setStaffsId(record.getStaffsId());
        recordModel.setId(record.getId());

        recordsExService.save(recordModel);

        return "redirect:/DangNhap/listRecord";
    }

    /**
	 * Delete rc.
	 *
	 * @param model    the model
	 * @param recordID the record ID
	 * @return the string
	 */
    @RequestMapping(value = "/deleteR/{recordID}", method = RequestMethod.GET)
    public String deleteRc(ModelMap model, @PathVariable(value = "recordID") String recordID) {
        recordsExService.deleteById(recordID);
        return "redirect:/DangNhap/listRecord";
    }
}
