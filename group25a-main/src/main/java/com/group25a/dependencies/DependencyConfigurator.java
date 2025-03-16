package com.group25a.dependencies;

import com.group25a.data_access.*;
import com.group25a.database.DBManager;
import com.group25a.database.IDBManager;
import com.group25a.services.*;

public class DependencyConfigurator {

    public static DependencyContainer configureDependencies() {
        var container = new DependencyContainer();

        IDBManager dbManager = new DBManager();
        container.addDependency(IDBManager.class, dbManager);

        IUserDataAccess userDataAccess = new UserDataAccess(dbManager);
        container.addDependency(IUserDataAccess.class, userDataAccess);

        IDoctorDataAccess doctorDataAccess = new DoctorDataAccess(dbManager);
        container.addDependency(IDoctorDataAccess.class, doctorDataAccess);

        IBookingDataAccess bookingDataAccess = new BookingDataAccess(dbManager);
        container.addDependency(IBookingDataAccess.class, bookingDataAccess);

        IPrescriptionDataAccess prescriptionDataAccess = new PrescriptionDataAccess(dbManager);
        container.addDependency(IPrescriptionDataAccess.class, prescriptionDataAccess);

        ILoggerDataAccess loggerDataAccess = new LoggerDataAccess(dbManager);
        container.addDependency(ILoggerDataAccess.class, loggerDataAccess);

        IUserService userService = new UserService(userDataAccess);
        container.addDependency(IUserService.class, userService);

        IDoctorService doctorService = new DoctorService(doctorDataAccess);
        container.addDependency(IDoctorService.class, doctorService);
        
        IBookingService bookingService = new BookingService(bookingDataAccess);
        container.addDependency(IBookingService.class, bookingService);

        IPrescriptionService prescriptionService = new PrescriptionService(prescriptionDataAccess);
        container.addDependency(IPrescriptionService.class, prescriptionService);

        ILoggerService loggerService = new LoggerService(loggerDataAccess);
        container.addDependency(ILoggerService.class, loggerService);

        return container;
    }
}
