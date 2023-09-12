import routes from '@constants/routes.ts'
import defineTitle from '@utils/defineTitle.ts'
import runOnce from '@utils/runOnce.ts'
import {FC} from 'react'
import {Button, Col, Container, Form, Row} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import {FadeIn} from '@components/animations/index.ts'
import useRegister from './hooks/useRegister.ts'

const RegisterPage: FC = () => {
  const {register, isRegistering, handleSubmit, onSubmit, errors, reset} =
    useRegister()

  runOnce(() => {
    defineTitle('Memo | Sign Up')
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
          <Col
            xs={10}
            sm={7}
            md={8}
            lg={6}
            xl={4}
            style={{
              backgroundColor: 'var(--snow)',
              borderRadius: 4,
              boxShadow: '0 0 4px var(--fade-dark)',
              padding: '32px 16px',
            }}
          >
            <Container>
              <h3 className="text-center" style={{color: 'var(--green)'}}>
                Sign Up
              </h3>
              <p
                className="text-center"
                style={{fontSize: '12px', color: 'var(--pale-dark)'}}
              >
                Save you notes with
                <span
                  className="text-italic ms-1"
                  style={{
                    color: 'var(--green)',
                  }}
                >
                  Memo
                </span>
                , sign up your account!
              </p>
            </Container>
            <Form onSubmit={handleSubmit(onSubmit)} className="px-2 mt-3">
              <Form.Group className="mb-2">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type={'text'}
                  placeholder={'Enter username'}
                  autoFocus
                  isInvalid={errors.username?.message ? true : false}
                  {...register('username')}
                />
                <span className="error">{errors.username?.message}</span>
              </Form.Group>
              <Form.Group className="mb-2">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type={'email'}
                  placeholder={'Enter email address'}
                  isInvalid={errors.email?.message ? true : false}
                  {...register('email')}
                />
                <span className="error">{errors.email?.message}</span>
              </Form.Group>
              <Form.Group className="mb-2">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type={'password'}
                  placeholder={'Enter password'}
                  isInvalid={errors.password?.message ? true : false}
                  {...register('password')}
                />
                <span className="error">{errors.password?.message}</span>
              </Form.Group>
              <Button
                type="submit"
                className="w-100 mt-2 d-flex justify-content-center align-items-center"
                disabled={isRegistering}
              >
                {isRegistering ? 'Loading...' : 'Sign up'}
              </Button>
              <Container>
                <p
                  className="mt-2 text-center"
                  style={{fontSize: '12px', color: 'var(--pale-dark)'}}
                >
                  Already have an account?
                  <Link to={routes.login} className="ms-1 text-decoration-none">
                    Sign In
                  </Link>
                  <br />
                  <span>
                    Had registered, but has not verified yet?
                    <Link
                      to={routes.accountVerify}
                      className="ms-1 text-decoration-none"
                    >
                      Click Here!
                    </Link>
                  </span>
                </p>
              </Container>
            </Form>
          </Col>
        </Row>
      </FadeIn>
    </Container>
  )
}

export default RegisterPage
