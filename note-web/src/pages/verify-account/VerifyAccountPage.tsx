import {FadeIn} from '@components/animations/index.ts'
import defineTitle from '@utils/defineTitle.ts'
import runOnce from '@utils/runOnce.ts'
import {FC} from 'react'
import {
  Button,
  Col,
  Container,
  FormControl,
  FormGroup,
  FormLabel,
  Row,
} from 'react-bootstrap'
import verifyAccountImg from '@assets/images/verifyAccount.jpg'
import {LazyLoadImage} from 'react-lazy-load-image-component'
import {Link, useSearchParams} from 'react-router-dom'
import routes from '@constants/routes.ts'
import useVerifyAccount from './hooks/useVerifyAccount.ts'

const VerifyAccountPage: FC = () => {
  const {register, errors, handleSubmit, setValue, onSubmit} =
    useVerifyAccount()
  const [params, _setParams] = useSearchParams({email: ''})

  runOnce(() => {
    defineTitle('Memo | Verify Account')
    if (params?.get('email')) setValue('email', params?.get('email')!)
  })

  return (
    <Container>
      <FadeIn>
        <Row
          style={{
            display: 'flex',
            justifyContent: 'center',
            flexWrap: 'wrap',
            padding: '180px 8px',
          }}
        >
          <Col xs={10} sm={9} md={8} lg={6} xxl={5}>
            <Row className="d-flex justify-content-center">
              <Col sm={10} lg={9}>
                <LazyLoadImage
                  style={{
                    width: '100%',
                    height: '400px',
                    boxShadow: '0 0 2px var(--fade-dark)',
                    borderRadius: '3px',
                  }}
                  src={verifyAccountImg}
                  effect={'blur'}
                  alt={'...'}
                  className="mx-auto"
                />
              </Col>
            </Row>
          </Col>
          <Col xs={10} sm={9} md={8} lg={6} xxl={5}>
            <Row>
              <Col xs={12} className="pt-5 p-5  ">
                <h3 className="text-start" style={{color: 'var(--green)'}}>
                  Verify Your Account
                </h3>
                <p
                  className="text-start"
                  style={{fontSize: '16px', color: 'var(--pale-dark)'}}
                >
                  Please go to your mail box for activate link or getting
                  verification code!
                </p>
                <Container className="p-0">
                  {!params.get('email') && (
                    <FormGroup className="mb-3">
                      <FormLabel>Email</FormLabel>
                      <FormControl
                        type="email"
                        className="w-100"
                        placeholder="Registered email address"
                        {...register('email')}
                      />
                      <span className="error">{errors.email?.message}</span>
                    </FormGroup>
                  )}
                  <FormGroup className="mb-3">
                    <FormLabel>Verification Code</FormLabel>
                    <FormControl
                      type="number"
                      className="w-100"
                      placeholder="XXXXXX"
                      {...register('code')}
                    />
                    <span className="error">{errors.code?.message}</span>
                  </FormGroup>
                  <FormGroup className="d-flex flex-column align-items-center gap-2">
                    <Button
                      onClick={handleSubmit(onSubmit)}
                      variant="primary"
                      className="w-100"
                    >
                      Verify
                    </Button>
                    <span
                      className={'error link'}
                      style={{
                        cursor: 'pointer',
                      }}
                    >
                      Resend
                    </span>
                  </FormGroup>
                  <FormGroup className="p-0 mt-3 d-flex flex-column gap-0 align-items-center">
                    <span
                      className="my-1 py-0"
                      style={{fontSize: '12px', color: 'var(--pale-dark)'}}
                    >
                      Haven't registered yet?
                      <Link
                        to={routes.register}
                        className="ms-1 text-decoration-none"
                      >
                        Sign up
                      </Link>
                    </span>
                    <span
                      className="my-1 py-0"
                      style={{
                        fontSize: 16,
                      }}
                    >
                      Or
                    </span>
                    <span
                      className="my-1 py-0"
                      style={{fontSize: '12px', color: 'var(--pale-dark)'}}
                    >
                      Already had account?{' '}
                      <Link
                        to={routes.login}
                        className="ms-1 text-decoration-none"
                      >
                        Sign in
                      </Link>
                    </span>
                  </FormGroup>
                </Container>
              </Col>
            </Row>
          </Col>
        </Row>
      </FadeIn>
    </Container>
  )
}

export default VerifyAccountPage
