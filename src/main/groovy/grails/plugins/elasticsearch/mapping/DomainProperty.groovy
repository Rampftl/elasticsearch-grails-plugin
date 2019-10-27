package grails.plugins.elasticsearch.mapping

//import grails.core.GrailsDomainClassProperty
import groovy.transform.CompileStatic
import org.grails.datastore.mapping.model.PersistentProperty

@CompileStatic
class DomainProperty {

    private final DomainReflectionService reflectionService

    private final DomainEntity owningEntity
    private final PersistentProperty delegate

    DomainProperty(DomainReflectionService reflectionService,
                   DomainEntity owningEntity,
                   PersistentProperty propertyDelegate)
    {
        this.reflectionService = reflectionService
        this.owningEntity = owningEntity
        this.delegate = propertyDelegate
    }

    boolean isPersistent() {
        //delegate.persistent
        true
    }

    DomainEntity getDomainEntity() {
        owningEntity
    }

    Class<?> getType() {
        delegate.type
    }

    String getName() {
        delegate.name
    }

    Class<?> getReferencedPropertyType() {
        (association) ? associationType : type
    }

    boolean isAssociation() {
        domainEntity.isAssociation(name)
    }

    Class<?> getAssociationType() {
        domainEntity.getAssociationForProperty(name)
    }

    DomainEntity getReferencedDomainEntity() {
        (association) ? reflectionService.getDomainEntity(associationType) : null
    }

    String getTypePropertyName() {
        delegate.getType().name
    }

    @Override
    String toString() {
        "DomainProperty{name=$name, type=${type.simpleName}, domainClass=$domainEntity.fullName}"
    }

}
