# Eclipse Management API

This is the [gmbal-commons project](https://github.com/eclipse-ee4j/orb-gmbal-commons).

To build:
```
mvn clean install
```

## Releasing

1. Visit the [CI](https://ci.eclipse.org/orb/view/Release/job/release-and-deploy/) and follow the instructions.
2. Create a PR for the release branch
3. Close the milestone
4. Create a Release.
5. Merge the release branch.

## Publishing the site

Do the following:

```
cd target/checkout
mvn verify site site:stage scm-publish:publish-scm
```
