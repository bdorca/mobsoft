package fleet.dork.btb.hu.fleet;

@Singleton
@Component(modules = {UIModule.class})
public interface FleetApplicationComponent {
    void inject(MainActivity mainActivity);

}